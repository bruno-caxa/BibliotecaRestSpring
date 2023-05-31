package curso.api.rest.exception;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import curso.api.rest.exception.dto.ApiErrorDto;
import curso.api.rest.exception.dto.ErrorDto;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    
    private static final String UNKNOWN_ERROR_KEY = "unknown.error";

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    private final MessageSource messageSource;

    public ExceptionHandlerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ApiErrorDto> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
    //     logger.error("Exception {}, Message: {}", exception.getClass().getName(), exception.getMessage());
    //     Set<ErrorDto> errors = exception.getBindingResult()
    //             .getFieldErrors()
    //             .stream()
    //             .map(error -> buildError(error.getCode(), error.getDefaultMessage()))
    //             .collect(Collectors.toSet());

    //     return ResponseEntity
    //             .status(HttpStatus.BAD_REQUEST)
    //             .body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
    // }

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ApiErrorDto> handlerBaseException(Throwable exception) {
        logger.error("Exception {}", exception.getClass().getName());
        MessageException messageException = (MessageException) exception;
        ErrorDto error = buildError(messageException.getExceptionKey(), bindExceptionKeywords(messageException.getMapDetails(),
        																					  messageException.getExceptionKey()));

        ApiErrorDto apiErrorDto = baseErrorBuilder(getResponseStatus(exception), error);

        return ResponseEntity
                .status(getResponseStatus(exception))
                .body(apiErrorDto);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorDto> handlerMethodThrowable(Throwable exception) {
        logger.error("Exception {}, Message: {}", exception.getClass().getName(), exception.getMessage());
        ErrorDto errors = buildError(UNKNOWN_ERROR_KEY, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(baseErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errors));
    }

    private ErrorDto buildError(String code, String message) {
        return new ErrorDto(code, message);
    }

    private ApiErrorDto baseErrorBuilder(HttpStatus httpStatus, ErrorDto error) {
        return new ApiErrorDto (
                new Date(),
                httpStatus.value(),
                httpStatus.name(),
                error);
    }

    private String bindExceptionKeywords(Map<String, Object> keywords, String exceptionKey) {
        String message = messageSource.getMessage(exceptionKey, null, LocaleContextHolder.getLocale());
        return Objects.nonNull(keywords) ? new StringSubstitutor(keywords).replace(message) : message;
    }

    private HttpStatus getResponseStatus(Throwable exception) {
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        if (exception.getClass().getAnnotation(ResponseStatus.class) == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return responseStatus.value();
    }
}
package curso.api.rest.exception.dto;

import java.util.Date;
import java.util.Set;

public class ApiErrorDto {

	private Date timestamp;
	private Integer status;
	private String code;
	private Set<ErrorDto> errors;

	public ApiErrorDto() {

	}

	public ApiErrorDto(Date timestamp, Integer status, String code, Set<ErrorDto> errors) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.code = code;
		this.errors = errors;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<ErrorDto> getErrors() {
		return errors;
	}

	public void setErrors(Set<ErrorDto> errors) {
		this.errors = errors;
	}

}

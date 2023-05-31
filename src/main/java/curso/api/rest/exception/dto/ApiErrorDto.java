package curso.api.rest.exception.dto;

import java.util.Date;

public class ApiErrorDto {

	private Date timestamp;
	private Integer status;
	private String code;
	private ErrorDto error;

	public ApiErrorDto() {

	}

	public ApiErrorDto(Date timestamp, Integer status, String code, ErrorDto error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.code = code;
		this.error = error;
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

	public ErrorDto getError() {
		return error;
	}

	public void setErrors(ErrorDto error) {
		this.error = error;
	}

}

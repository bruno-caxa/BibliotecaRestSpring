package curso.api.rest.exception.dto;

public class ErrorDto {

	private String key;
	private String message;

	public ErrorDto() {

	}

	public ErrorDto(String key, String message) {
		super();
		this.key = key;
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

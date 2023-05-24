package curso.api.rest.exception.business;

import java.util.Map;

import curso.api.rest.exception.BaseRuntimeException;

public class JwtExpiredException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String KEY = "rule.jwt.expired";
	
	public JwtExpiredException(String value) {
		super(Map.of("value", value));
	}
	
	@Override
	public String getExceptionKey() {
		return KEY;
	}

}

package curso.api.rest.exception;

import java.util.Map;

public interface MessageException {
	
	public String getExceptionKey();
	
    public Map<String, Object> getMapDetails();
}

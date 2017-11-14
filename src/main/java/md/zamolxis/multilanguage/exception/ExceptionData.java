package md.zamolxis.multilanguage.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

public class ExceptionData {

	public enum ExceptionDataType {

		UNDEFINED(""),
		UNHANDLED_PESISTENCE_EXCEPTION("Unhandled persistence exception {0} has occured."),
		UNHANDLED_RUNTIME_EXCEPTION("Unhandled runtime exception {0} has occured."),
		ENTITY_NOT_DEFINED("{0} not defined."), 
		CREATE_UNIQUE_VIOLATION("Cannot save {0} - identity or unique key(s) violation."), 
		READ_NOT_FOUND("{0} not found."), 
		UPDATE_OBSOLETE_TIMESTAMP("Cannot update {0} - obsolete timestamp (re-read data is needed)."),
		DELETE_OBSOLETE_TIMESTAMP("Cannot delete {0} - obsolete timestamp (re-read data is needed).");

		private String pattern;

		ExceptionDataType(String pattern) {
			this.pattern = pattern;
		}

		public String getPattern() {
			return pattern;
		}

	}

	private ExceptionDataType exceptionDataType;
	private String pattern;
	private Object[] parameters;
	private String message;
	private HttpStatus httpStatus;

	public ExceptionData(ExceptionDataType exceptionDataType, String pattern, Object... parameters) {
		if (exceptionDataType == null){
			exceptionDataType = ExceptionDataType.UNDEFINED;
		}
		this.exceptionDataType = exceptionDataType;
		if (this.exceptionDataType != ExceptionDataType.UNDEFINED){
			pattern = exceptionDataType.getPattern(); 
		}
		this.pattern = pattern;
		this.parameters = parameters;
		this.message = MessageFormat.format(pattern, parameters);
	}

	public ExceptionDataType getExceptionDataType() {
		return exceptionDataType;
	}

	public void setExceptionDataType(ExceptionDataType exceptionDataType) {
		this.exceptionDataType = exceptionDataType;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
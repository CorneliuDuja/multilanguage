package md.zamolxis.multilanguage.exception;

import java.text.MessageFormat;

import md.zamolxis.multilanguage.exception.ExceptionData.ExceptionDataType;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 6564639389371235123L;
	private ExceptionData exceptionData;

	public ServiceException(ExceptionDataType exceptionDataType, Object... parameters) {
		super(MessageFormat.format(exceptionDataType.getPattern(), parameters));
		this.exceptionData = new ExceptionData(exceptionDataType, "", parameters);
	}

	public ServiceException(String pattern, Object... parameters) {
		super(MessageFormat.format(pattern, parameters));
		this.exceptionData = new ExceptionData(null, pattern, parameters);
	}

	public ExceptionData getExceptionData() {
		return exceptionData;
	}

	public void setExceptionData(ExceptionData exceptionData) {
		this.exceptionData = exceptionData;
	}

}
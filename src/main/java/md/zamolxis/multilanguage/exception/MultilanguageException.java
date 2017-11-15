package md.zamolxis.multilanguage.exception;

import java.text.MessageFormat;

import md.zamolxis.multilanguage.exception.MultilanguageExceptionData.MultilanguageExceptionDataType;

public class MultilanguageException extends Exception {

	private static final long serialVersionUID = 6564639389371235123L;
	private MultilanguageExceptionData multilanguageExceptionData;

	public MultilanguageException(MultilanguageExceptionDataType multilanguageExceptionDataType, Object... parameters) {
		super(MessageFormat.format(multilanguageExceptionDataType.getPattern(), parameters));
		this.multilanguageExceptionData = new MultilanguageExceptionData(multilanguageExceptionDataType, "", parameters);
	}

	public MultilanguageException(String pattern, Object... parameters) {
		super(MessageFormat.format(pattern, parameters));
		this.multilanguageExceptionData = new MultilanguageExceptionData(null, pattern, parameters);
	}

	public MultilanguageExceptionData getMultilanguageExceptionData() {
		return multilanguageExceptionData;
	}

	public void setMultilanguageExceptionData(MultilanguageExceptionData multilanguageExceptionData) {
		this.multilanguageExceptionData = multilanguageExceptionData;
	}

}
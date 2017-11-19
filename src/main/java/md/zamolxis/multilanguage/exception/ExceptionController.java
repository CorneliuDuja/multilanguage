package md.zamolxis.multilanguage.exception;

import javax.persistence.PersistenceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import md.zamolxis.multilanguage.exception.MultilanguageExceptionData.MultilanguageExceptionDataType;

@ControllerAdvice
public class ExceptionController {

	private ResponseEntity<MultilanguageExceptionData> create(MultilanguageExceptionData multilanguageExceptionData) {
		return new ResponseEntity<MultilanguageExceptionData>(multilanguageExceptionData,
				multilanguageExceptionData.getHttpStatus());
	}

	private MultilanguageExceptionData unhandled(String message) {
		return new MultilanguageExceptionData(MultilanguageExceptionDataType.UNHANDLED_RUNTIME_EXCEPTION, "", message);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MultilanguageExceptionData> handle(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		MultilanguageExceptionData multilanguageExceptionData;
		FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError();
		if (fieldError == null) {
			multilanguageExceptionData = unhandled(methodArgumentNotValidException.getMessage());
		} else {
			multilanguageExceptionData = new MultilanguageExceptionData(
					MultilanguageExceptionDataType.ENTITY_PROPERTY_NOT_VALID, "", fieldError.getField(),
					fieldError.getObjectName(), fieldError.getDefaultMessage());
		}
		multilanguageExceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);
		return create(multilanguageExceptionData);
	}

	@ExceptionHandler(MultilanguageException.class)
	public ResponseEntity<MultilanguageExceptionData> handle(MultilanguageException multilanguageException) {
		MultilanguageExceptionData multilanguageExceptionData = multilanguageException.getMultilanguageExceptionData();
		multilanguageExceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);
		return create(multilanguageExceptionData);
	}

	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<MultilanguageExceptionData> handle(PersistenceException persistenceException) {
		MultilanguageExceptionData multilanguageExceptionData = new MultilanguageExceptionData(
				MultilanguageExceptionDataType.UNHANDLED_PESISTENCE_EXCEPTION, "", persistenceException.getMessage());
		multilanguageExceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);
		return create(multilanguageExceptionData);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<MultilanguageExceptionData> handle(RuntimeException runtimeException) {
		MultilanguageExceptionData multilanguageExceptionData = unhandled(runtimeException.getMessage());
		multilanguageExceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);
		return create(multilanguageExceptionData);
	}

}

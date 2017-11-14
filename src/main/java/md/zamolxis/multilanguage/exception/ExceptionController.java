package md.zamolxis.multilanguage.exception;

import javax.persistence.PersistenceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import md.zamolxis.multilanguage.exception.ExceptionData.ExceptionDataType;

@ControllerAdvice
public class ExceptionController {

	private ResponseEntity<ExceptionData> create(ExceptionData exceptionData) {
		return new ResponseEntity<ExceptionData>(exceptionData, exceptionData.getHttpStatus());
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ExceptionData> handle(ServiceException serviceLogicException) {
		ExceptionData exceptionData = serviceLogicException.getExceptionData();
		exceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);
		return create(exceptionData);
	}

	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<ExceptionData> handle(PersistenceException persistenceException) {
		ExceptionData exceptionData = new ExceptionData(ExceptionDataType.UNHANDLED_PESISTENCE_EXCEPTION, "",
				persistenceException.getMessage());
		exceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);
		return create(exceptionData);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionData> handle(RuntimeException runtimeException) {
		ExceptionData exceptionData = new ExceptionData(ExceptionDataType.UNHANDLED_RUNTIME_EXCEPTION, "",
				runtimeException.getMessage());
		exceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);
		return create(exceptionData);
	}

}

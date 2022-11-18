package team.asd.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

//	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//	public ResponseError handleException(Exception exception) {
//		log.error(exception.getMessage(), exception);
//		return new ResponseError(exception.getMessage(), HttpStatus.NOT_FOUND.value());
//	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseError handleValidationException(ValidationException exception) {
		log.error(exception.getMessage(), exception);
		return new ResponseError(exception.getMessage(), HttpStatus.NOT_FOUND.value());
	}
}

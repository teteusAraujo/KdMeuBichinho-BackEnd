package com.kdmeubichinho.exception;

import com.kdmeubichinho.dto.CustomExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ResponseEntity<CustomExceptionDTO> handleCustomSimpleException(ValidationException validationException) {
		HttpStatus responseStatus = validationException.getHttpStatus();
		return new ResponseEntity<>(new CustomExceptionDTO(validationException.getDescription()), responseStatus);
	}

	@ExceptionHandler(JpaSystemException.class)
	@ResponseBody
	public ResponseEntity<CustomExceptionDTO> handleJpaException(JpaSystemException ex) {
		String[] errors = ex.getCause().getCause().toString().split("#");
		String errorMessage = "Ocorreu um erro ao tentar executar a operação";
		if(errors.length > 1){
			errorMessage = errors[2];
		}
		return new ResponseEntity<>(new CustomExceptionDTO(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalStateException.class)
	@ResponseBody
	public ResponseEntity<CustomExceptionDTO> handleIllegalStateException(IllegalStateException ex) {
		return new ResponseEntity<>(new CustomExceptionDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

/**
 * 
 */
package com.deutschebank.trading.exception;

import static com.deutschebank.trading.constants.ErrorConstants.FAILED_TO_READ_REQUEST;
import static com.deutschebank.trading.constants.ErrorConstants.INTERNAL_SERVER_ERROR;
import static com.deutschebank.trading.constants.ErrorConstants.VALIDATION_FAILED;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception Handler class which handles all the possible exceptions
 * 
 * @author veluchamy.jeganathan
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			details.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		ErrorDetails errorDetails = new ErrorDetails(new Date(), VALIDATION_FAILED, details);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date(), FAILED_TO_READ_REQUEST, details);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles the {@link Exception} and maps the appropriate error
	 * messages
	 * 
	 * @param ex      - thrown {@link Exception}
	 * @param request - web request
	 * @return - customized error message with appropriate status code
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleGenericException(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		ex.printStackTrace();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), INTERNAL_SERVER_ERROR, details);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

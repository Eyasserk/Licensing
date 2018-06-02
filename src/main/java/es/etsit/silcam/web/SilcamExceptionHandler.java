package es.etsit.silcam.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import es.etsit.silcam.exception.AuthenticationException;
import es.etsit.silcam.exception.NotFoundException;
import es.etsit.silcam.exception.BadRequestException;
import es.etsit.silcam.exception.ConflictException;

import es.etsit.silcam.bean.response.ErrorResponse;
import es.etsit.silcam.util.Constants;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class SilcamExceptionHandler {
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleUnauthorized(AuthenticationException e) {
		log.error("Returning HTTP 401. AuthenticationException: {}", e.getMessage());
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.UNAUTHORIZED.value());
		error.setMessage(Constants.EXCEPTION_AUTH_FAILED);

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("Returning HTTP 400. MethodArgumentNotValidException: {}", e.getMessage());
		return create400Response(e);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException e) {
		log.error("Returning HTTP 400. HttpMessageNotReadableException: {}", e.getMessage());
		return create400Response(e);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException e) {
		log.error("Returning HTTP 400. IllegalArgumentException: {}", e.getMessage());
		return create400Response(e);
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleSizeLimitExceededException(MultipartException e) {
		log.error("Returning HTTP 400. MultipartException: {}", e.getMessage());
		return create400Response(e);
	}

	private ResponseEntity<ErrorResponse> create400Response(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(
				"Invalid input field " + fieldErrors.get(0).getField() + ": " + fieldErrors.get(0).getDefaultMessage());

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorResponse> create400Response(HttpMessageNotReadableException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Cannot parse the body contained in the request.");

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorResponse> create400Response(IllegalArgumentException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorResponse> create400Response(MultipartException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleException(RuntimeException e) {
		log.error("General Error: " + e);
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("General Error: " + e);

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleException(NotFoundException e) {
		log.error("Returning HTTP 404. NotFoundException: {}", e.getMessage());
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleException(BadRequestException e) {
		log.error("Returning HTTP 400. BadRequestException: {}", e.getMessage());
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ResponseEntity<ErrorResponse> handleException(ConflictException e) {
		log.error("Returning HTTP 409. ConflictException: {}", e.getMessage());
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.CONFLICT.value());
		error.setMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
	}

}

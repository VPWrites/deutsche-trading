package com.deutsche.trading.handler;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deutsche.trading.exception.SignalNotFoundException;
import com.deutsche.trading.util.Messages;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleException(Exception e) {

		logger.error(Messages.INTERNAL_SERVER_ERROR, e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Messages.INTERNAL_SERVER_ERROR + e.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {

		logger.error(Messages.BAD_REQUEST, e.getMessage());
		return ResponseEntity.badRequest().body(Messages.BAD_REQUEST + e.getMessage());
	}

	@ExceptionHandler(SignalNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleSignalNotFoundException(SignalNotFoundException e) {

		logger.error(Messages.SIGNAL_NOT_FOUND, e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Messages.SIGNAL_NOT_FOUND + e.getMessage());
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {

		logger.error(Messages.UNAUTHORIZED, e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Messages.UNAUTHORIZED + e.getMessage());
	}
}

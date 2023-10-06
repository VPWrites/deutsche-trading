package com.deutsche.trading.exception;

public class DeutscheTradingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9027227601844966213L;

	public DeutscheTradingException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DeutscheTradingException(String message) {
		super(message);
	}
}

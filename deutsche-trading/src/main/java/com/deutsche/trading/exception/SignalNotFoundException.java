package com.deutsche.trading.exception;

public class SignalNotFoundException extends DeutscheTradingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3792835207877192426L;

	public SignalNotFoundException(String message) {
		super(String.format(message));
	}
}

package com.deutsche.trading.exception;

public class SignalNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1703044964335068522L;

	public SignalNotFoundException(String message) {
        super(message);
    }
}
package com.deutsche.trading.handler;

import org.springframework.stereotype.Component;

import com.deutsche.trading.util.Signal;

@Component
public class Signal1Handler implements SignalHandler {

	@Override
	public void handleSignal(Signal signal) {
		// Implement the specific logic for handling Signal set up.
		System.out.println("Handling Signal set up: Implementing Signal set up logic.");
	}
}
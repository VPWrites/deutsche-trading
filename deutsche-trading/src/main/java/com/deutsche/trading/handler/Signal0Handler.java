package com.deutsche.trading.handler;

import org.springframework.stereotype.Component;

import com.deutsche.trading.util.Signal;

@Component
public class Signal0Handler implements SignalHandler {

	@Override
	public void handleSignal(Signal signal) {
		// Implement the specific logic for handling default signal.
		System.out.println("Handling default Signal: Implementing default Signal logic.");
	}
}
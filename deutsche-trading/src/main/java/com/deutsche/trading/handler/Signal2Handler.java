package com.deutsche.trading.handler;

import org.springframework.stereotype.Component;

import com.deutsche.trading.util.Signal;

@Component
public class Signal2Handler implements SignalHandler {

	@Override
	public void handleSignal(Signal signal) {
		// Implement the specific logic for handling Signal reverse.
		System.out.println("Handling Signal reverse: Implementing Signal reverse logic.");
	}
}
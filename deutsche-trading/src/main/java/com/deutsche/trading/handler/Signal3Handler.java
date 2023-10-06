package com.deutsche.trading.handler;

import org.springframework.stereotype.Component;

import com.deutsche.trading.util.Signal;

@Component
public class Signal3Handler implements SignalHandler {

	@Override
	public void handleSignal(Signal signal) {
		// Implement the specific logic for setting Algo Params.
		System.out.println("Setting Algo Params: Implementing algo params logic.");
	}
}
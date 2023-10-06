package com.deutsche.trading.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutsche.trading.exception.DeutscheTradingException;
import com.deutsche.trading.exception.SignalNotFoundException;
import com.deutsche.trading.handler.SignalHandler;
import com.deutsche.trading.util.Messages;
import com.deutsche.trading.util.Signal;

@Service
public class SignalHandlerService {

	private static final Logger logger = LoggerFactory.getLogger(SignalHandlerService.class);

	private final Map<Integer, SignalHandler> handlers;

	@Autowired
	public SignalHandlerService(ListableBeanFactory beanFactory) {
		this.handlers = new HashMap<>();
		Map<String, SignalHandler> handlerBeans = beanFactory.getBeansOfType(SignalHandler.class);
		handlerBeans.forEach((beanName, handler) -> {
			int signalValue = getSignalValueFromBeanName(beanName);
			handlers.put(signalValue, handler);
		});
	}

	public void handleSignal(int signalValue) {
		Signal signal = Signal.fromInt(signalValue);
		SignalHandler handler = handlers.getOrDefault(signal.getSignalValue(),
				handlers.get(Signal.DEFAULT.getSignalValue()));
		try {
			handler.handleSignal(signal);
		} catch (Exception e) {
			logger.error(Messages.HANDLING_SIGNAL_FAILED + signal, e);
			throw new DeutscheTradingException(Messages.HANDLING_SIGNAL_FAILED + signal, e);
		}
	}

	// Utility method to extract signal value from bean name
	private int getSignalValueFromBeanName(String beanName) {
		// Extract the numeric part from the bean name using regular expression
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(beanName);

		// Check if there is a numeric part in the bean name
		if (matcher.find()) {
			return Integer.parseInt(matcher.group());
		} else {
			logger.error(Messages.INVALID_SIGNAL);
			throw new SignalNotFoundException(Messages.INVALID_SIGNAL);
		}
	}
}

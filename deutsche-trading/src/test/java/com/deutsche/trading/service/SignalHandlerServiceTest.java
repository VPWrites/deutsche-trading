package com.deutsche.trading.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.ListableBeanFactory;

import com.deutsche.trading.exception.SignalNotFoundException;
import com.deutsche.trading.handler.SignalHandler;
import com.deutsche.trading.util.Messages;
import com.deutsche.trading.util.Signal;


class SignalHandlerServiceTest {

	@Mock
	private ListableBeanFactory beanFactory;

	@Mock
	private SignalHandler defaultHandler;

	@Mock
	private SignalHandler signal1Handler;

	@Mock
	private SignalHandler signal2Handler;

	@Mock
	private SignalHandler signal3Handler;

	@InjectMocks
	private SignalHandlerService signalHandlerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Mock the beans that will be injected into the service
		when(beanFactory.getBeansOfType(SignalHandler.class)).thenReturn(Map.of("signal0Handler", defaultHandler,
				"signal1Handler", signal1Handler, "signal2Handler", signal2Handler, "signal3Handler", signal3Handler));

		// Set up the handlers map manually for testing
		Map<Integer, SignalHandler> handlers = new HashMap<>();
		handlers.put(0, defaultHandler);
		handlers.put(1, signal1Handler);
		handlers.put(2, signal2Handler);
		handlers.put(3, signal3Handler);

		try {
			var handlersField = SignalHandlerService.class.getDeclaredField("handlers");
			handlersField.setAccessible(true);
			handlersField.set(signalHandlerService, handlers);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException("Failed to set handlers field via reflection", e);
		}
	}

	@Test
	void handleSignal_ValidSignal_CallsSignal1Handler() {

		Signal signal = Signal.fromInt(1);

		signalHandlerService.handleSignal(1);

		verify(signal1Handler, times(1)).handleSignal(signal);
		verify(defaultHandler, never()).handleSignal(signal);
	}

	@Test
	void handleSignal_Signal0_CallsDefaultHandler() {

		Signal signal = Signal.fromInt(0);

		signalHandlerService.handleSignal(0);

		verify(defaultHandler, times(1)).handleSignal(signal);
		verify(signal1Handler, never()).handleSignal(signal);
	}

	@Test
	void handleSignal_Signal2_CallsSignal2Handler() {

		Signal signal = Signal.fromInt(2);

		signalHandlerService.handleSignal(2);

		verify(signal2Handler, times(1)).handleSignal(signal);
	}

	@Test
	void handleSignal_Signal3_CallsSignal3Handler() {

		Signal signal = Signal.fromInt(3);

		signalHandlerService.handleSignal(3);

		verify(signal3Handler, times(1)).handleSignal(signal);
	}

	@Test
	void handleSignal_InvalidSignal_ThrowsException() {
		// Arrange
		int invalidSignalValue = 4;

		SignalNotFoundException exception = assertThrows(SignalNotFoundException.class, () -> {
			signalHandlerService.handleSignal(invalidSignalValue);
		});

		// Assert
		assertThat(exception).isNotNull();
		verify(defaultHandler, never()).handleSignal(any());
		verify(signal1Handler, never()).handleSignal(any());
		assertThat(exception.getMessage()).isEqualTo(Messages.INVALID_SIGNAL);
	}
}
package com.deutsche.trading.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.deutsche.trading.service.SignalHandlerService;
import com.deutsche.trading.util.Messages;

class SignalControllerTest {

	@Mock
	private SignalHandlerService signalHandlerService;

	@InjectMocks
	private SignalController signalController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void fetchSignal_ValidSignal_Success() {
		// Arrange
		int validSignalValue = 1;

		// Act
		ResponseEntity<String> responseEntity = signalController.fetchSignal(validSignalValue);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(Messages.SIGNAL_RECEIVED, responseEntity.getBody());
		verify(signalHandlerService, times(1)).handleSignal(validSignalValue);
	}

	@Test
	void fetchSignal_InvalidSignal_BadRequest() {
		// Arrange
		int invalidSignalValue = 4;
		String errorMessage = Messages.SIGNAL_NOT_FOUND;

		// Act
		ResponseEntity<String> responseEntity = signalController.fetchSignal(invalidSignalValue);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals(errorMessage, responseEntity.getBody());
		verify(signalHandlerService, never()).handleSignal(invalidSignalValue);
	}
}

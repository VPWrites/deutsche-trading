package com.deutsche.trading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deutsche.trading.service.SignalHandlerService;
import com.deutsche.trading.util.Messages;

@RestController
@RequestMapping("/trading")
public class SignalController {

    private static final int MIN_SIGNAL_VALUE = 0;
	private static final int MAX_SIGNAL_VALUE = 3;
	
	private final SignalHandlerService signalHandlerService;

    @Autowired
    public SignalController(SignalHandlerService signalHandlerService) {
        this.signalHandlerService = signalHandlerService;
    }

    @PostMapping("/fetch-signal")
    public ResponseEntity<String> fetchSignal(@RequestParam("signal") int signalValue) {
        
    	 if (isValidSignal(signalValue)) {
             signalHandlerService.handleSignal(signalValue);
             return ResponseEntity.status(HttpStatus.OK).body(Messages.SIGNAL_RECEIVED);
         } else {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.SIGNAL_NOT_FOUND);
         }
     }

     private boolean isValidSignal(int signalValue) {
         // Add logic to validate the signal value, return true if valid, false otherwise
         // For example, check if signalValue is within a certain range or meets specific criteria.
         // Return true if valid, false if invalid.
         return signalValue >= MIN_SIGNAL_VALUE && signalValue <= MAX_SIGNAL_VALUE;
     }
 }

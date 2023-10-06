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

    private final SignalHandlerService signalHandlerService;

    @Autowired
    public SignalController(SignalHandlerService signalHandlerService) {
        this.signalHandlerService = signalHandlerService;
    }

    @PostMapping("/fetch-signal")
    public ResponseEntity<String> fetchSignal(@RequestParam("signal") int signalValue) {
        
        signalHandlerService.handleSignal(signalValue);
        return ResponseEntity.status(HttpStatus.OK).body(Messages.SIGNAL_RECEIVED);
    }
}

package com.deutsche.trading.util;

import com.deutsche.trading.exception.SignalNotFoundException;

public enum Signal {
    SIGNAL1(1),
    SIGNAL2(2),
    SIGNAL3(3),
    DEFAULT(0);

    private final int signalValue;

    Signal(int signalValue) {
        this.signalValue = signalValue;
    }

    public int getSignalValue() {
        return signalValue;
    }

    public static Signal fromInt(int value) {
        for (Signal signal : Signal.values()) {
            if (signal.signalValue == value) {
                return signal;
            }
        }
       throw new SignalNotFoundException(Messages.INVALID_SIGNAL);
    }
}

package com.deutsche.trading.util;

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
        return DEFAULT; // Default signal if the provided integer does not match any enum constant
    }
}

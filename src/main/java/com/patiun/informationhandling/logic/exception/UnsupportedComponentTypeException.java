package com.patiun.informationhandling.logic.exception;

public class UnsupportedComponentTypeException extends Exception {

    public UnsupportedComponentTypeException() {
    }

    public UnsupportedComponentTypeException(String message) {
        super(message);
    }

    public UnsupportedComponentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedComponentTypeException(Throwable cause) {
        super(cause);
    }

    public UnsupportedComponentTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

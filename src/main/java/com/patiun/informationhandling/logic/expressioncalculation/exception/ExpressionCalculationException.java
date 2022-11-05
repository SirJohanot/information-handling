package com.patiun.informationhandling.logic.expressioncalculation.exception;

public class ExpressionCalculationException extends Exception {

    public ExpressionCalculationException() {
    }

    public ExpressionCalculationException(String message) {
        super(message);
    }

    public ExpressionCalculationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionCalculationException(Throwable cause) {
        super(cause);
    }

    public ExpressionCalculationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

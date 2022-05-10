package com.barkpark.exceptions;

/**
 * Exception to throw when a given park ID is not found in the database.
 */
public class ParkNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8435207429171961888L;

    /**
     * Exception with no message or cause.
     */
    public ParkNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ParkNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public ParkNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public ParkNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

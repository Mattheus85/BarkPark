package com.barkpark.exceptions;

public class ReviewNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6670143216838681171L;

    /**
     * Exception with no message or cause.
     */
    public ReviewNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ReviewNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public ReviewNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

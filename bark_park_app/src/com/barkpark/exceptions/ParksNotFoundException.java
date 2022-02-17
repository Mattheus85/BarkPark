package com.barkpark.exceptions;

/**
 * Exception to throw when no parks are found in the database.
 */
public class ParksNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3087106421956056322L;

    /**
     * Exception with no message or cause.
     */
    public ParksNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ParksNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public ParksNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public ParksNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

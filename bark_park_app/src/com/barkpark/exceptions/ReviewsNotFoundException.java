package com.barkpark.exceptions;

public class ReviewsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8534263059778504895L;

    /**
     * Exception with no message or cause.
     */
    public ReviewsNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ReviewsNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public ReviewsNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public ReviewsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

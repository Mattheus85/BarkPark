package com.barkpark.exceptions;

public class LocationsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2009020625890325032L;

    /**
     * Exception with no message or cause.
     */
    public LocationsNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public LocationsNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public LocationsNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public LocationsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

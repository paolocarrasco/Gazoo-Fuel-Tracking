package pe.com.abaris.gazoo.exceptions;

import static pe.com.abaris.gazoo.exceptions.ErrorType.DEFAULT;

/**
 * Class: GazooException
 * is the general exception we can find in the app.
 */
public class GazooException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * prop: code
     * represents the code of this error
     */
    private final ErrorType errorType;

    public GazooException() {
        errorType = DEFAULT;
    }

    public GazooException(final ErrorType type) {
        super();
        errorType = type;
    }

    public GazooException(final String message, final ErrorType type) {
        super(message);
        errorType = type;
    }

    public GazooException(final Throwable cause, final ErrorType type) {
        super(cause);
        errorType = type;
    }

    public GazooException(final String message, final Throwable cause,
            final ErrorType type) {
        super(message, cause);
        errorType = type;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}

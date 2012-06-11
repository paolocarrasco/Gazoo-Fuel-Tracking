package pe.com.abaris.gazoo.exceptions;

import static pe.com.abaris.gazoo.exceptions.ErrorType.DEFAULT;

import java.util.Map;

/**
 * Class: ErrorMapLoader
 * contains the map of the error messages.
 */
public class ErrorMapLoader implements MapLoader {

    private Map<ErrorType, String> errorMessages;

    /**
     * Here receives the error messages
     */
    public ErrorMapLoader(Map<ErrorType, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    public String getMessage(ErrorType type) {
        return errorMessages.get(type);
    }

    @Override
    public boolean containsMessage(ErrorType type) {
        return errorMessages.containsKey(type);
    }

    @Override
    public String getDefaultMessage() {
        assert (errorMessages.containsKey(DEFAULT)) : "There is no default error message!";
        return errorMessages.get(DEFAULT);
    }

}

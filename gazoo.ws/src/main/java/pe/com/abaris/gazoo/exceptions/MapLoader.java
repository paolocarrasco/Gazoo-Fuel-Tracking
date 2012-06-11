package pe.com.abaris.gazoo.exceptions;

/**
 * Interface: MapLoader
 * is the contract for loading the map with the corresponding messages.
 */
public interface MapLoader {
    
    /**
     * Method: getMessage
     * returns a message for the given error type.
     */
    String getMessage(ErrorType type);
    
    /**
     * Method: containsMessage
     * indicates if the given error type is mapped.
     */
    boolean containsMessage(ErrorType type);
    
    /**
     * Method: getDefaultMessage
     * brings the default message.
     */
    String getDefaultMessage();
}

package pe.com.abaris.gazoo.exceptions;

/**
 * Enum: ErrorType
 * represents an error type in the application
 */
public enum ErrorType {
    /**
     * Const: DEFAULT
     * is the default error in the app.
     */
    DEFAULT,
    /**
     * Const: ARGUMENTS
     * is an error when arguments are wrong.
     */
    ARGUMENTS,
    /**
     * Const: CONNECTION
     * is an error when connecting to something.
     */
    CONNECTION,
    /**
     * Const: DATASOURCE
     * is an error when dealing with a data source.
     */
    DATASOURCE
}

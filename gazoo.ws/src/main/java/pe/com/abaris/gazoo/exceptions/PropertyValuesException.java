package pe.com.abaris.gazoo.exceptions;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Class: GazooArgumentsException
 * represents an error when the property values of an entity are invalid.
 */
public class PropertyValuesException extends GazooException {

    private static final long serialVersionUID = 1L;

    private final Set<String> propertyNames;

    public PropertyValuesException(final String propertyName) {
        super("The property values of the entity are not valid",
                ErrorType.ARGUMENTS);
        propertyNames = new HashSet<String>();
        propertyNames.add(propertyName);
    }

    public PropertyValuesException(final Set<String> propertyNames) {
        this.propertyNames = propertyNames;
    }

    public Set<String> getPropertyNames() {
        return unmodifiableSet(propertyNames);
    }
}

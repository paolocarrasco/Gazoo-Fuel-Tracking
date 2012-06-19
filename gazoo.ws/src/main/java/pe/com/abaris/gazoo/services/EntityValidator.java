package pe.com.abaris.gazoo.services;

import java.util.Set;

import pe.com.abaris.gazoo.exceptions.GazooException;
import pe.com.abaris.gazoo.model.BaseEntity;

/**
 * Interface: EntityValidator
 * is the contract for validating the property values of a given entity
 * according to the business logic.
 */
public interface EntityValidator<T extends BaseEntity<?>> {

    /**
     * Method: hasValidProperties
     * validates if the property values are according to the business logic.
     * 
     * Returns:
     * *true* if all the properties are valid.
     */
    boolean hasValidProperties(T entity);

    /**
     * Method: listValidationErrors
     * lists all the properties that aren't valid.
     */
    Set<String> listInvalidProperties();

    /**
     * Method: assertFilledArguments
     * checks if the mandatory arguments were filled.
     * 
     * Throws: GazooException
     * if at least one of the mandatory arguments weren't filled.
     */
    void assertFilledMandatoryArguments(T entity) throws GazooException;

}

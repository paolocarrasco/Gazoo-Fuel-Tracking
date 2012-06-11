package pe.com.abaris.gazoo.services;

/**
 * Enumeration: FilterCriterion
 * represents the criteria that can be applied to a search.
 */
public enum FilterCriterion {
    /**
     * Const: EQUAL
     * Equal to...
     */
    EQUAL,
    /**
     * Const: LIKE
     * Like...
     */
    LIKE,
    /**
     * Const: GREATER
     * Greater than...
     */
    GREATER,
    /**
     * Const: GREATER_EQUAL
     * Greater than or equal to...
     */
    GREATER_EQUAL,
    /**
     * Const: LESS
     * Less than...
     */
    LESS,
    /**
     * Const: LESS_EQUAL
     * Less than or equal to...
     */
    LESS_EQUAL
}

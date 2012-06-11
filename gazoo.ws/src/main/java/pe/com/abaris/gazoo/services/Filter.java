package pe.com.abaris.gazoo.services;

/**
 * Class: Filter
 * represents the filters to be applied to a search.
 */
public class Filter {

    private FilterCriterion criterion;
    private String value;

    public FilterCriterion getCriterion() {
        return criterion;
    }

    public void setCriterion(final FilterCriterion criterion) {
        this.criterion = criterion;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}

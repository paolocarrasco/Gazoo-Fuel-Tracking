package pe.com.abaris.gazoo.model;

/**
 * Class: BaseEntity
 * is the base for all the entities to be mapped.
 */
public abstract class BaseEntity<T> {

    private T id;

    public T getId() {
        return id;
    }

    public void setId(final T id) {
        this.id = id;
    }
}

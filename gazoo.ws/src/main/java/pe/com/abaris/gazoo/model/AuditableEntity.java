package pe.com.abaris.gazoo.model;

import java.util.Date;

/**
 * Class: AuditableEntity
 * represents an entity that can be audited (we can track its creation and
 * modification).
 */
public abstract class AuditableEntity<T> extends BaseEntity<T> {

    /**
     * Prop: creation
     * is the first time the record was registered.
     */
    private Date creation;
    /**
     * Prop: modification
     * is the last time the record was modified.
     */
    private Date modification;
    /**
     * Prop: createdBy
     * is the person that created the record.
     */
    private String createdBy;
    /**
     * Prop: modifiedBy
     * is the last person that modified the record.
     */
    private String modifiedBy;

    public Date getCreation() {
        return creation;
    }

    public void setCreation(final Date creation) {
        this.creation = creation;
    }

    public Date getModification() {
        return modification;
    }

    public void setModification(final Date modification) {
        this.modification = modification;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}

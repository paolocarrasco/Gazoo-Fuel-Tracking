package pe.com.abaris.gazoo.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class: Vehicle
 * represents a motored vehicle. e.g. a car, a truck... not a bike, nor the
 * wheel chair of your grandpa.
 */
@XmlRootElement(name = "vehicle")
public class Vehicle extends AuditableEntity<String> {

    /**
     * Prop: owner
     * is the person who wants to track this vehicle (even if he doesn't own the
     * vehicle in the real life!!)
     */
    private String owner;
    /**
     * Prop: name
     * is the name the owner calls his vehicle (very common these crazy days).
     */
    private String name;
    /**
     * Prop: make
     * is the make that built the vehicle... someone had to made it, right?
     */
    private String make;
    /**
     * Prop: model
     * is the official name that the make labeled this kind of vehicles.
     */
    private String model;
    /**
     * Prop: initialMileage
     * is the status of the mileage when started the log of the vehicle.
     */
    private Integer initialMileage;
    /**
     * Prop: year
     * is the year the vehicle was made.
     */
    private Integer year;
    /**
     * Prop: color
     * is the current main color of the vehicle (forget about registering the
     * color
     * of the stripes and other 'fancy' decorations)
     */
    private String color;

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(final String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public Integer getInitialMileage() {
        return initialMileage;
    }

    public void setInitialMileage(final Integer initialMileage) {
        this.initialMileage = initialMileage;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

}

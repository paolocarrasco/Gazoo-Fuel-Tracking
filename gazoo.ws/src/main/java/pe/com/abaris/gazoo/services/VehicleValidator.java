package pe.com.abaris.gazoo.services;

import java.util.HashSet;
import java.util.Set;

import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Class: VehicleValidator
 * helps to validate the property values of a vehicle.
 */
public class VehicleValidator implements EntityValidator<Vehicle> {

    private final Set<String> invalidProperties = new HashSet<String>();

    private static final String ONLY_ALPHANUMERIC_PATTERN = "^\\w+$";

    @Override
    public boolean hasValidProperties(final Vehicle vehicle) {
        boolean containValidProperties = true;
        // ========== validating owner ==============
        final String owner = vehicle.getOwner();
        if (null == owner) {
            invalidProperties.add("owner");
            containValidProperties = false;
        }
        else {
            final String ownerTrimmed = owner.trim();
            if (ownerTrimmed.isEmpty()
                    || !ownerTrimmed.matches(ONLY_ALPHANUMERIC_PATTERN)) {
                invalidProperties.add("owner");
                containValidProperties = false;
            }
        }
        // ========== validating name ==============
        final String name = vehicle.getName();
        if (null == name) {
            invalidProperties.add("name");
            containValidProperties = false;
        }
        else {
            final String nameTrimmed = name.trim();
            if (nameTrimmed.isEmpty()
                    || !nameTrimmed.matches(ONLY_ALPHANUMERIC_PATTERN)) {
                invalidProperties.add("name");
                containValidProperties = false;
            }
        }
        // ========== validating color ==============
        final String color = vehicle.getColor();
        if (null != color) {
            final String colorTrimmed = color.trim();
            if (!colorTrimmed.matches(ONLY_ALPHANUMERIC_PATTERN)) {
                invalidProperties.add("color");
                containValidProperties = false;
            }
        }
        // ========== validating make ==============
        final String make = vehicle.getMake();
        if (null != make) {
            final String makeTrimmed = make.trim();
            if (!makeTrimmed.matches(ONLY_ALPHANUMERIC_PATTERN)) {
                invalidProperties.add("make");
                containValidProperties = false;
            }
        }
        // ========== validating model ==============
        final String model = vehicle.getModel();
        if (null != model) {
            final String modelTrimmed = model.trim();
            if (!modelTrimmed.matches(ONLY_ALPHANUMERIC_PATTERN)) {
                invalidProperties.add("model");
                containValidProperties = false;
            }
        }
        // ========== validating initialMileage ==============
        final Integer initialMileage = vehicle.getInitialMileage();
        if (null == initialMileage) {
            invalidProperties.add("initialMileage");
            containValidProperties = false;
        }
        else {
            if (initialMileage < 0) {
                invalidProperties.add("initialMileage");
                containValidProperties = false;
            }
        }
        // ========== validating year ==============
        final Integer year = vehicle.getYear();
        if (null != year) {
            if (year < 1850) {
                invalidProperties.add("year");
                containValidProperties = false;
            }
        }
        return containValidProperties;
    }

    @Override
    public Set<String> listInvalidProperties() {
        return invalidProperties;
    }

}

package pe.com.abaris.gazoo.services;

import static pe.com.abaris.gazoo.exceptions.ErrorType.ARGUMENTS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.abaris.gazoo.exceptions.GazooException;
import pe.com.abaris.gazoo.exceptions.PropertyValuesException;
import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Class: VehicleServiceImpl
 * contains the main logic for the vehicle
 */
public class VehicleServiceImpl implements VehicleService {

    private final Map<String, Vehicle> vehicles = new HashMap<String, Vehicle>();
    private EntityValidator<Vehicle> vehicleValidator;

    @Override
    public void register(final Vehicle vehicle) {
        if (vehicle == null) {
            throw new GazooException("The vehicle is missing", ARGUMENTS);
        }
        final String name = vehicle.getName();
        final String owner = vehicle.getOwner();
        if (owner == null || name == null
                || vehicle.getInitialMileage() == null) {
            throw new GazooException(
                    "Any of the mandatory fields weren't set when registering a vehicle",
                    ARGUMENTS);
        }
        if (vehicles.containsKey(name.trim())) {
            throw new GazooException(String.format(
                    "The vehicle %s was registered before", name), ARGUMENTS);
        }
        // TODO maybe we could move this into another class to nullify the empty
        // values by reflection (decreasing a little bit the performance)
        final String color = vehicle.getColor();
        if (null != color && color.trim().isEmpty()) {
            vehicle.setColor(null);
        }
        final String make = vehicle.getMake();
        if (null != make && make.trim().isEmpty()) {
            vehicle.setMake(null);
        }
        final String model = vehicle.getModel();
        if (null != model && model.trim().isEmpty()) {
            vehicle.setModel(null);
        }
        if (!vehicleValidator.hasValidProperties(vehicle)) {
            throw new PropertyValuesException(
                    vehicleValidator.listInvalidProperties());
        }
        vehicles.put(name, vehicle);
        vehicle.setId(String.valueOf(vehicles.size()));
    }

    @Override
    public void update(final Vehicle vehicle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(final String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Vehicle> search(final List<Filter> filters) {
        // TODO Auto-generated method stub
        return null;
    }

    public EntityValidator<Vehicle> getVehicleValidator() {
        return vehicleValidator;
    }

    public void setVehicleValidator(
            final EntityValidator<Vehicle> vehicleValidator) {
        this.vehicleValidator = vehicleValidator;
    }
}

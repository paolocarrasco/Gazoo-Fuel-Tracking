package pe.com.abaris.gazoo.services;

import static pe.com.abaris.gazoo.exceptions.ErrorType.ARGUMENTS;
import static pe.com.abaris.gazoo.exceptions.ErrorType.DUPLICATION;

import java.util.Arrays;
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
// TODO analyze wisely when to throw exceptions
// TODO (throwing every problem as exception is not a good practice at all)
public class VehicleServiceImpl implements VehicleService {

    private final Map<String, Vehicle> vehicles = new HashMap<String, Vehicle>();
    private EntityValidator<Vehicle> vehicleValidator;

    @Override
    public void register(final Vehicle vehicle) {
        // performing the overall validation
        overallValidation(vehicle, new ExistanceCheckCallback() {

            @Override
            public void doExist(final boolean exist) {
                if (exist) {
                    throw new GazooException(String.format(
                            "The vehicle %s was registered before",
                            vehicle.getName()), DUPLICATION);
                }
            }
        });

        // performing the registration and storing the generated ID
        final String generatedId = performRegistration(vehicle);

        // setting the generated ID of the vehicle
        vehicle.setId(generatedId);
    }

    @Override
    public void update(final Vehicle vehicle) {
        overallValidation(vehicle, new ExistanceCheckCallback() {

            @Override
            public void doExist(final boolean exist) {
                if (!exist) {
                    throw new GazooException(String.format(
                            "The vehicle %s wasn't registered before",
                            vehicle.getName()), ARGUMENTS);
                }
            }
        });

        // performing the update of the vehicle
        performUpdate(vehicle);
    }

    @Override
    public void delete(final String vehicleName) {
        // the vehicle name should be set
        if (vehicleName == null) {
            throw new GazooException("The vehicle name wasn't set", ARGUMENTS);
        }

        // if the vehicle is found, it should be deleted
        if (get(vehicleName) != null) {
            performDelete(vehicleName);
        }
        else {
            throw new GazooException(String.format(
                    "The vehicle %s wasn't registered before", vehicleName),
                    ARGUMENTS);
        }
    }

    @Override
    public Vehicle get(final String id) {
        return performRetrieval(id);
    }

    @Override
    public List<Vehicle> search(final String make, final String model,
            final String year, final String color) {
        return Arrays.asList(vehicles.values().toArray(new Vehicle[0]));
    }

    public EntityValidator<Vehicle> getVehicleValidator() {
        return vehicleValidator;
    }

    public void setVehicleValidator(
            final EntityValidator<Vehicle> vehicleValidator) {
        this.vehicleValidator = vehicleValidator;
    }

    private String performRegistration(final Vehicle vehicle) {
        vehicles.put(vehicle.getName(), vehicle);
        return String.valueOf(vehicles.size());
    }

    private boolean exist(final Vehicle vehicle) {
        return vehicles.containsKey(vehicle.getName().trim());
    }

    private void performUpdate(final Vehicle vehicle) {
        vehicles.put(vehicle.getName(), vehicle);
    }

    private void performDelete(final String vehicleName) {
        vehicles.remove(vehicleName);
    }

    private Vehicle performRetrieval(final String id) {
        return vehicles.get(id);
    }

    private void overallValidation(final Vehicle vehicle,
            final ExistanceCheckCallback callback) {
        if (vehicle == null) {
            throw new GazooException("The vehicle is missing", ARGUMENTS);
        }
        // assert if the mandatory arguments had values
        vehicleValidator.assertFilledMandatoryArguments(vehicle);

        callback.doExist(exist(vehicle));

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
    }

    /**
     * It receives if the vehicle exists in the data source.
     */
    interface ExistanceCheckCallback {
        void doExist(boolean exist);
    }
}

package pe.com.abaris.gazoo.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import pe.com.abaris.gazoo.exceptions.GazooException;
import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Class: VehicleServiceRegistrationTest
 * tests the functionality of the update of the VehicleServiceImpl class.
 */
public class VehicleServiceUpdateTest extends AbstractVehicleServiceTest {

    @Before
    public void setUp() throws Exception {
        // this is the vehicle to update throughout this test
        final Vehicle vehicle;
        vehicle = new Vehicle();
        // setting the mandatory properties
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        vehicle.setModel(VEHICLE_MODEL);

        getVehicleService().register(vehicle);
    }

    @Test
    public void update() { // ToUpdate
        final Vehicle vehicle = new Vehicle();
        // setting the same old values
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        // updating it to another make and model
        vehicle.setMake(ANOTHER_VEHICLE_MAKE);
        vehicle.setModel(ANOTHER_VEHICLE_MODEL);

        getVehicleService().update(vehicle);

        final Vehicle vehicleRef = getVehicleService().get(VEHICLE_NAME);
        assertThat("The vehicle make should be updated", vehicleRef.getMake(),
                is(ANOTHER_VEHICLE_MAKE));
        assertThat("The vehicle model should be updated",
                vehicleRef.getModel(), is(ANOTHER_VEHICLE_MODEL));
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenVehicleWasntRegisteredBefore() {
        // this is a vehicle not registered before
        final Vehicle anotherVehicle = new Vehicle();
        anotherVehicle.setOwner(VEHICLE_OWNER);
        anotherVehicle.setName("differentName");
        anotherVehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        anotherVehicle.setMake(ANOTHER_VEHICLE_MAKE);

        getVehicleService().update(anotherVehicle);
    }
}

package pe.com.abaris.gazoo.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pe.com.abaris.gazoo.exceptions.GazooException;
import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Class: VehicleServiceDeletionTest
 * tests the functionality of the deletion of the VehicleServiceImpl class.
 * 
 */
public class VehicleServiceDeletionTest extends AbstractVehicleServiceTest {

    @Before
    public void setUp() throws Exception {
        // this is the vehicle to be deleted throughout this test
        final Vehicle vehicle = new Vehicle();
        // setting the mandatory properties
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        vehicle.setModel(VEHICLE_MODEL);

        getVehicleService().register(vehicle);
    }

    @After
    public void tearDown() throws Exception {
        final VehicleServiceImpl vehicleService = getVehicleService();
        final Vehicle vehicle = vehicleService.get(VEHICLE_NAME);
        if (vehicle != null) {
            vehicleService.delete(VEHICLE_NAME);
        }
    }

    @Test
    public void delete() {
        final VehicleServiceImpl vehicleService = getVehicleService();
        vehicleService.delete(VEHICLE_NAME);
        final Vehicle vehicle = vehicleService.get(VEHICLE_NAME);
        assertThat(vehicle, is(nullValue()));
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsNotFound() {
        getVehicleService().delete(ANOTHER_VEHICLE_NAME);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsNull() {
        getVehicleService().delete(null);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsEmpty() {
        getVehicleService().delete(" ");
    }
}

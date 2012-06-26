package pe.com.abaris.gazoo.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
    public void update() { // To Update
        // this is the same test as for
        // "should update when the new name is existing duplicate"
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

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenVehicleIsNull() {
        getVehicleService().update(null);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenVehicleIsEmpty() {
        getVehicleService().update(new Vehicle());
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsNull() {
        // creating the vehicle of a shy guy
        final Vehicle vehicle = new Vehicle();
        // this is the vehicle with no name
        final Vehicle anotherVehicle = new Vehicle();
        anotherVehicle.setOwner(VEHICLE_OWNER);
        anotherVehicle.setName(null);
        anotherVehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        anotherVehicle.setMake(VEHICLE_MAKE);

        getVehicleService().update(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsEmpty() {
        // creating the vehicle of another shy guy
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        // a valid name should contain any alphanumeric char
        vehicle.setName(" ");
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);

        // updating the vehicle with an empty name
        getVehicleService().update(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenColorContainsNonAlphanumericChars() {
        // creating the vehicle with hippie colors
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        // a valid color should contain just alphanumeric chars
        vehicle.setColor("!@");

        // updating the vehicle with an invalid color
        getVehicleService().update(vehicle);
    }

    @Test
    public void shouldAcceptColorAsNullWhenColorIsEmpty() {
        // creating the transparent vehicle
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        // setting the color as empty
        vehicle.setColor(" ");

        // updating the vehicle with an invalid color
        getVehicleService().update(vehicle);

        assertThat("the color should be null now", vehicle.getColor(),
                is(nullValue()));
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameContainsSpaces() {
        // creating the spaceship
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        // a valid name should contain just alphanumeric chars
        vehicle.setName("an evil name");
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        vehicle.setColor("red");

        // updating the vehicle with an invalid name
        getVehicleService().update(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameContainsNonAlphanumericChars() {
        // creating the super vehicle
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        // a valid name should contain just alphanumeric chars
        vehicle.setName("an extremely evil name!");
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        vehicle.setColor("red");

        // updating the vehicle with an invalid name
        getVehicleService().update(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenInitialMileageIsNegative() {
        // creating the vehicle that went backwards
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        // a valid initial mileage is a natural number
        vehicle.setInitialMileage(-50);
        vehicle.setMake(VEHICLE_MAKE);
        vehicle.setColor("red");

        // updating the vehicle with an invalid initial mileage
        getVehicleService().update(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenMakeContainsNonAlphanumericChars() {
        // creating a hand-crafted vehicle
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        // a valid make should contain just alphanumeric chars
        vehicle.setMake("me?");
        vehicle.setColor("red");

        // updating the vehicle with an invalid make
        getVehicleService().update(vehicle);
    }

    @Test
    public void shouldAcceptMakeAsNullWhenMakeIsEmpty() {
        // creating a vehicle without make
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        // setting the make as empty
        vehicle.setMake(" ");
        vehicle.setColor("red");

        // updating the vehicle with an empty make
        getVehicleService().update(vehicle);

        assertThat("the vehicle make should be now null", vehicle.getMake(),
                is(nullValue()));
    }

    @Test
    public void shouldAcceptModelAsNullWhenModelIsEmpty() {
        // creating a vehicle without model
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        // setting the model as empty
        vehicle.setMake(VEHICLE_MAKE);
        vehicle.setModel(" ");

        // updating the vehicle with an empty make
        getVehicleService().update(vehicle);

        assertThat("the vehicle model should be now null", vehicle.getModel(),
                is(nullValue()));
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenModelContainsNonAlphanumericChars() {
        // creating the very exclusive vehicle (with a pretty weird model)
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        // a valid model should contain just alphanumeric chars
        vehicle.setModel("Laris%a");
        vehicle.setColor("red");

        // updating the vehicle with an invalid model
        getVehicleService().update(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenYearIsNegative() {
        // creating the Flintstones' car
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        // a valid year should be A.C.
        vehicle.setYear(-1980);

        // updating the vehicle with an invalid year
        getVehicleService().update(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenYearIsTooLongAgo() {
        // creating the vehicle of 'Back to the future'
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        // the first motored vehicle wasn't yet created at this time
        vehicle.setYear(1550);

        // updating the vehicle with an invalid year
        getVehicleService().update(vehicle);
    }

}

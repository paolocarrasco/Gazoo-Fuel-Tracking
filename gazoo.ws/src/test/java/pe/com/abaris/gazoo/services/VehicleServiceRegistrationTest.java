package pe.com.abaris.gazoo.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import pe.com.abaris.gazoo.exceptions.GazooException;
import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Class: VehicleServiceRegistrationTest
 * tests the functionality of the registration of the VehicleServiceImpl class.
 */
public class VehicleServiceRegistrationTest extends AbstractVehicleServiceTest {

    @Test
    public void register() {
        // creating the valid vehicle
        final Vehicle vehicle = new Vehicle();
        // setting the mandatory properties
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);

        getVehicleService().register(vehicle);

        assertThat("an ID should be generated", vehicle.getId(),
                is(notNullValue()));
    }

    @Test
    public void registerTwoDifferentVehicles() {
        // creating a vehicle...
        final Vehicle vehicleA = new Vehicle();
        vehicleA.setOwner(VEHICLE_OWNER);
        vehicleA.setName(VEHICLE_NAME + "A");
        vehicleA.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicleA.setMake(VEHICLE_MAKE);
        vehicleA.setModel(VEHICLE_MODEL);

        // registering the vehicle
        getVehicleService().register(vehicleA);

        // creating another vehicle...
        final Vehicle vehicleB = new Vehicle();
        vehicleB.setOwner(VEHICLE_OWNER);
        vehicleB.setName(VEHICLE_NAME + "B");
        vehicleB.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicleB.setMake(ANOTHER_VEHICLE_MAKE);
        vehicleB.setModel(ANOTHER_VEHICLE_MODEL);

        // trying to register the another vehicle
        getVehicleService().register(vehicleB);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenOwnerIsNotSet() {
        // creating the indie vehicle
        final Vehicle vehicle = new Vehicle();
        // not setting the owner of the vehicle
        vehicle.setColor(VEHICLE_COLOR);
        vehicle.setInitialMileage(500);

        getVehicleService().register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsNotSet() {
        // creating the unnamely vehicle
        final Vehicle vehicle = new Vehicle();
        // not setting the name of the vehicle
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setColor(VEHICLE_COLOR);
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);

        getVehicleService().register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenInitialMileageIsNotSet() {
        // creating the vehicle whose mileage went beyond borders
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        vehicle.setName(VEHICLE_NAME);
        vehicle.setMake(VEHICLE_MAKE);

        getVehicleService().register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenVehicleIsNull() {
        // registering a null vehicle
        getVehicleService().register(null);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameWasRegisteredBefore() {
        // creating a vehicle...
        final Vehicle vehicleA = new Vehicle();
        vehicleA.setOwner(VEHICLE_OWNER);
        vehicleA.setName(VEHICLE_NAME);
        vehicleA.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicleA.setMake(VEHICLE_MAKE);
        vehicleA.setModel(VEHICLE_MODEL);

        // registering the vehicle
        getVehicleService().register(vehicleA);

        // creating another vehicle with the same name...
        final Vehicle vehicleB = new Vehicle();
        vehicleB.setOwner(VEHICLE_OWNER);
        vehicleB.setName(VEHICLE_NAME);
        vehicleB.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicleB.setMake(ANOTHER_VEHICLE_MAKE);
        vehicleB.setModel(ANOTHER_VEHICLE_MODEL);

        // trying to register a vehicle with the same name
        getVehicleService().register(vehicleB);
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

        // registering the vehicle with an invalid color
        getVehicleService().register(vehicle);
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

        // registering the vehicle with an invalid color
        getVehicleService().register(vehicle);

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

        // registering the vehicle with an invalid name
        getVehicleService().register(vehicle);
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

        // registering the vehicle with an invalid name
        getVehicleService().register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsEmpty() {
        // creating the vehicle of a shy guy
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner(VEHICLE_OWNER);
        // a valid name should contain any alphanumeric char
        vehicle.setName(" ");
        vehicle.setInitialMileage(VEHICLE_INITIAL_MILEAGE);
        vehicle.setMake(VEHICLE_MAKE);
        vehicle.setColor("red");

        // registering the vehicle with an empty name
        getVehicleService().register(vehicle);
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

        // registering the vehicle with an invalid initial mileage
        getVehicleService().register(vehicle);
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

        // registering the vehicle with an invalid make
        getVehicleService().register(vehicle);
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

        // registering the vehicle with an empty make
        getVehicleService().register(vehicle);

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

        // registering the vehicle with an empty make
        getVehicleService().register(vehicle);

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

        // registering the vehicle with an invalid model
        getVehicleService().register(vehicle);
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

        // registering the vehicle with an invalid year
        getVehicleService().register(vehicle);
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

        // registering the vehicle with an invalid year
        getVehicleService().register(vehicle);
    }

}

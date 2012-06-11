package pe.com.abaris.gazoo.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import pe.com.abaris.gazoo.exceptions.GazooException;
import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Class: VehicleServiceRegistrationTest
 * tests the functionality of the registration of the VehicleServiceImpl class.
 */
@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceRegistrationTest {

    
    
    /**
     * Variable: vehicleService
     * is the target of the test
     */
    @Spy
    private VehicleServiceImpl vehicleService = new VehicleServiceImpl();
    
    private EntityValidator<Vehicle> vehicleValidator = new VehicleValidator();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vehicleService.setVehicleValidator(vehicleValidator);
    }

    @Test
    public void register() {
        // creating the valid vehicle
        final Vehicle vehicle = new Vehicle();
        // Setting the mandatory properties
        vehicle.setOwner("testowner");
        vehicle.setName("validName");
        vehicle.setInitialMileage(9400);

        vehicleService.register(vehicle);

        assertThat("an ID should be generated", vehicle.getId(),
                is(notNullValue()));
    }

    @Test
    public void registerTwoDifferentVehicles() {
        // creating a vehicle...
        final Vehicle vehicleA = new Vehicle();
        vehicleA.setOwner("testowner");
        vehicleA.setName("nameA");
        vehicleA.setInitialMileage(5000);
        vehicleA.setMake("Toyoba");
        vehicleA.setModel("Laris");

        // registering the vehicle
        vehicleService.register(vehicleA);

        // creating another vehicle...
        final Vehicle vehicleB = new Vehicle();
        vehicleB.setOwner("testowner");
        vehicleB.setName("nameB");
        vehicleB.setInitialMileage(5000);
        vehicleB.setMake("Revault");
        vehicleB.setModel("Trio");

        // trying to register the another vehicle
        vehicleService.register(vehicleB);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenOwnerIsNotSet() {
        // creating the indie vehicle
        final Vehicle vehicle = new Vehicle();
        // not setting the owner of the vehicle
        vehicle.setColor("blue");
        vehicle.setInitialMileage(9400);

        vehicleService.register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsNotSet() {
        // creating the unnamely vehicle
        final Vehicle vehicle = new Vehicle();
        // not setting the name of the vehicle
        vehicle.setOwner("testowner");
        vehicle.setColor("blue");
        vehicle.setInitialMileage(9400);

        vehicleService.register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenInitialMileageIsNotSet() {
        // creating the vehicle whose mileage went beyond borders
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("theName");
        vehicle.setMake("Toyoba");

        vehicleService.register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenVehicleIsNull() {
        // registering a null vehicle
        vehicleService.register(null);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameWasRegisteredBefore() {
        // creating a vehicle...
        final Vehicle vehicleA = new Vehicle();
        vehicleA.setOwner("testowner");
        vehicleA.setName("theName");
        vehicleA.setInitialMileage(5000);
        vehicleA.setMake("Toyoba");
        vehicleA.setModel("Laris");

        // registering the vehicle
        vehicleService.register(vehicleA);

        // creating another vehicle with the same name...
        final Vehicle vehicleB = new Vehicle();
        vehicleB.setOwner("testowner");
        vehicleB.setName("theName");
        vehicleB.setInitialMileage(5000);
        vehicleB.setMake("Revault");
        vehicleB.setModel("Trio");

        // trying to register a vehicle with the same name
        vehicleService.register(vehicleB);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenColorContainsNonAlphanumericChars() {
        // creating the vehicle with hippie colors
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("theName");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        // a valid color should contain just alphanumeric chars
        vehicle.setColor("!@");

        // registering the vehicle with an invalid color
        vehicleService.register(vehicle);
    }

    @Test
    public void shouldAcceptColorAsNullWhenColorIsEmpty() {
        // creating the transparent vehicle
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("theName");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        // setting the color as empty
        vehicle.setColor(" ");

        // registering the vehicle with an invalid color
        vehicleService.register(vehicle);

        assertThat("the color should be null now", vehicle.getColor(),
                is(nullValue()));
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameContainsSpaces() {
        // creating the spaceship
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        // a valid name should contain just alphanumeric chars
        vehicle.setName("the name");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        vehicle.setColor("red");

        // registering the vehicle with an invalid name
        vehicleService.register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameContainsNonAlphanumericChars() {
        // creating the super vehicle
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        // a valid name should contain just alphanumeric chars
        vehicle.setName("thename!");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        vehicle.setColor("red");

        // registering the vehicle with an invalid name
        vehicleService.register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenNameIsEmpty() {
        // creating the vehicle of a shy guy
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        // a valid name should contain any alphanumeric char
        vehicle.setName(" ");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        vehicle.setColor("red");

        // registering the vehicle with an empty name
        vehicleService.register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenInitialMileageIsNegative() {
        // creating the vehicle that went backwards
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("thename!");
        // a valid initial mileage is a natural number
        vehicle.setInitialMileage(-50);
        vehicle.setMake("Toyoba");
        vehicle.setColor("red");

        // registering the vehicle with an invalid initial mileage
        vehicleService.register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenMakeContainsNonAlphanumericChars() {
        // creating a hand-crafted vehicle
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("thename");
        vehicle.setInitialMileage(5000);
        // a valid make should contain just alphanumeric chars
        vehicle.setMake("me?");
        vehicle.setColor("red");

        // registering the vehicle with an invalid make
        vehicleService.register(vehicle);
    }

    @Test
    public void shouldAcceptMakeAsNullWhenMakeIsEmpty() {
        // creating a vehicle without make
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("thename");
        vehicle.setInitialMileage(5000);
        // setting the make as empty
        vehicle.setMake(" ");
        vehicle.setColor("red");

        // registering the vehicle with an empty make
        vehicleService.register(vehicle);

        assertThat("the vehicle make should be now null", vehicle.getMake(),
                is(nullValue()));
    }

    @Test
    public void shouldAcceptModelAsNullWhenModelIsEmpty() {
        // creating a vehicle without model
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("thename");
        vehicle.setInitialMileage(5000);
        // setting the model as empty
        vehicle.setMake("Toyoba");
        vehicle.setModel(" ");

        // registering the vehicle with an empty make
        vehicleService.register(vehicle);

        assertThat("the vehicle model should be now null", vehicle.getModel(),
                is(nullValue()));
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenModelContainsNonAlphanumericChars() {
        // creating the very exclusive vehicle (with a pretty weird model)
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("thename");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        // a valid model should contain just alphanumeric chars
        vehicle.setModel("Laris%a");
        vehicle.setColor("red");

        // registering the vehicle with an invalid model
        vehicleService.register(vehicle);
    }

    public void shouldAcceptTheModelAsNullWhenModelIsEmpty() {
        // creating the vehicle with an empty model
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("thename");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        // setting the model as empty
        vehicle.setModel(" ");
        vehicle.setColor("red");

        // registering the vehicle with an empty model
        vehicleService.register(vehicle);

        assertThat("the vehicle model should be null now", vehicle.getModel(),
                is(nullValue()));
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenYearIsNegative() {
        // creating the flinstones' car
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("thename");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        // a valid year should be A.C.
        vehicle.setYear(-1980);

        // registering the vehicle with an invalid year
        vehicleService.register(vehicle);
    }

    @Test(expected = GazooException.class)
    public void shouldThrowAnErrorWhenYearIsTooLongAgo() {
        // creating the vehicle of 'Back to the future'
        final Vehicle vehicle = new Vehicle();
        vehicle.setOwner("testowner");
        vehicle.setName("thename");
        vehicle.setInitialMileage(5000);
        vehicle.setMake("Toyoba");
        // the first motored vehicle wasn't yet created at this time
        vehicle.setYear(1550);

        // registering the vehicle with an invalid year
        vehicleService.register(vehicle);
    }

}

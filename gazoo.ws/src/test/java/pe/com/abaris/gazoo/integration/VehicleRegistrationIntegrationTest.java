package pe.com.abaris.gazoo.integration;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static javax.ws.rs.core.Response.Status.*;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pe.com.abaris.gazoo.model.Vehicle;
import pe.com.abaris.gazoo.ws.vehicle.VehicleCollectionManagerImpl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Class: VehicleRegistrationIntegrationTest
 * make integration tests with the registration of vehicles.
 */
public class VehicleRegistrationIntegrationTest {

    /**
     * The vehicle name to use for the tests
     */
    private static final String VEHICLE_NAME = "theName";

    /**
     * Holds the URI of the host we target for the tests
     */
    // TODO make this URI configurable (it has the API version!)
    private static final String HOST_URI = "http://localhost:8080/gazoo/1.0.0/ws";
    
    /**
     * The service to be tested
     */
    private WebResource service;

    @Before
    public void setUp() throws Exception {
        service = Client.create(new DefaultClientConfig()).resource(HOST_URI);
    }

    @After
    public void tearDown() {
        // deleting the registration
        service.path(
                VehicleCollectionManagerImpl.VEHICLES_RESOURCE
                        .replace(VehicleCollectionManagerImpl.USERNAME_PARAM,
                                "testuser")).path(VEHICLE_NAME)
                .accept(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);
    }

    @Test
    public void register() {
        // setting the vehicle to be registered
        final Vehicle vehicle = new Vehicle();
        vehicle.setName(VEHICLE_NAME);
        vehicle.setInitialMileage(8000);

        // registering the vehicle to the service
        final ClientResponse response = registerVehicle(vehicle);

        // making the assertions
        assertThat("Should return CREATED", response.getStatus(),
                is(CREATED.getStatusCode()));
        assertThat("Should give us the URL",
                URI.create(response.getHeaders().get("Location").get(0)),
                is(URI.class));
    }

    @Test
    public void shouldReturnBadRequestWhenInitialMileageIsNotSet() {
        // setting the vehicle to be registered without mileage
        final Vehicle vehicle = new Vehicle();
        vehicle.setInitialMileage(8000);

        // registering the vehicle to the service
        final ClientResponse response = registerVehicle(vehicle);

        // making the assertions
        assertThat("Should return BAD_REQUEST", response.getStatus(),
                is(BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void shouldReturnBadRequestWhenNameIsNotSet() {
        // setting the vehicle to be registered without name
        final Vehicle vehicle = new Vehicle();
        vehicle.setName(VEHICLE_NAME);

        // registering the vehicle to the service
        final ClientResponse response = registerVehicle(vehicle);

        // making the assertions
        assertThat("Should return BAD_REQUEST", response.getStatus(),
                is(BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void shouldReturnBadRequestWhenVehicleIsNul() {
        // registering the vehicle to the service
        final ClientResponse response = registerVehicle(null);

        // making the assertions
        assertThat("Should return BAD_REQUEST", response.getStatus(),
                is(BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void shouldReturnBadRequestWhenVehicleIsEmpty() {
        // registering the vehicle to the service
        final ClientResponse response = registerVehicle(new Vehicle());

        // making the assertions
        assertThat("Should return BAD_REQUEST", response.getStatus(),
                is(BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void shouldReturnNotModifiedWhenNameWasRegisteredBefore() {
        // setting the first vehicle
        final Vehicle vehicleA = new Vehicle();
        vehicleA.setName(VEHICLE_NAME);
        vehicleA.setInitialMileage(8000);

        // registering the vehicle to the service
        final ClientResponse responseA = registerVehicle(vehicleA);

        // making the assertions
        assertThat("Should return CREATED", responseA.getStatus(),
                is(CREATED.getStatusCode()));
        assertThat("Should give us the URL",
                URI.create(responseA.getHeaders().get("Location").get(0)),
                is(URI.class));

        // setting the second vehicle to be registered
        final Vehicle vehicleB = new Vehicle();
        vehicleB.setName(VEHICLE_NAME);
        vehicleB.setInitialMileage(8000);

        // registering the second vehicle to the service
        final ClientResponse responseB = registerVehicle(vehicleB);

        // making the assertions
        assertThat("Should return NOT_MODIFIED", responseB.getStatus(),
                is(NOT_MODIFIED.getStatusCode()));
    }

    @Test
    public void shouldReturnBadRequestWhenAnyFieldIsNotValid() {
        // TODO for this test, we should split it into a method for each field
    }

    private ClientResponse registerVehicle(final Vehicle vehicle) {
        return service
                .path(VehicleCollectionManagerImpl.VEHICLES_RESOURCE
                        .replace(VehicleCollectionManagerImpl.USERNAME_PARAM,
                                "testuser")).accept(MediaType.APPLICATION_JSON)
                .entity(vehicle, MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);
    }

}

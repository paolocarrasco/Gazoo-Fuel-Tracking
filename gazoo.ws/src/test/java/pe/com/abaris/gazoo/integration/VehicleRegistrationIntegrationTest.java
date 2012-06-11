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
     * The service to be tested
     */
    private WebResource service;

    /**
     * Holds the URI of the host we target for the tests
     */
    // TODO make this URI configurable
    private static final String HOST_URI = "http://localhost:8080/gazoo/1.0.0/ws";

    @Before
    public void setUp() throws Exception {
        service = Client.create(new DefaultClientConfig()).resource(HOST_URI);
    }

    @After
    public void tearDown() {
        // TODO rollback the registration
    }

    @Test
    public void register() {
        // setting the vehicle to be registered
        final Vehicle vehicle = new Vehicle();
        vehicle.setName("myName");
        vehicle.setInitialMileage(8000);

        // registering the vehicle to the service
        final ClientResponse response = service
                .path(VehicleCollectionManagerImpl.VEHICLES_RESOURCE.replace(VehicleCollectionManagerImpl.USERNAME_PARAM, "testuser"))
                .accept(MediaType.APPLICATION_JSON)
                .entity(vehicle, MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        // making the assertions
        assertThat("Should return CREATED", response.getStatus(),
                is(CREATED.getStatusCode()));
        assertThat("Should give us the URL",
                URI.create(response.getHeaders().get("Location").get(0)),
                is(URI.class));
    }

    @Test
    public void shouldReturnBadRequestWhenInitialMileageIsNotSet() {

    }

    @Test
    public void shouldReturnBadRequestWhenNameIsNotSet() {

    }

    @Test
    public void shouldReturnBadRequestWhenVehicleIsNul() {

    }

    @Test
    public void shouldReturnBadRequestWhenVehicleIsEmpty() {

    }

    @Test
    public void shouldReturnNotModifiedWhenNameWasRegisteredBefore() {

    }

    @Test
    public void shouldReturnBadRequestWhenAnyFieldIsNotValid() {
        // TODO for this test, we should split it into one method for each field
    }

}

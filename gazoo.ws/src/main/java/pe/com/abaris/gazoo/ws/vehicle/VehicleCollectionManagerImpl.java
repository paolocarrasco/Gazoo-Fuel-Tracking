package pe.com.abaris.gazoo.ws.vehicle;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.abaris.gazoo.exceptions.ErrorType;
import pe.com.abaris.gazoo.exceptions.GazooException;
import pe.com.abaris.gazoo.model.Vehicle;
import pe.com.abaris.gazoo.services.VehicleService;

import com.sun.jersey.api.core.HttpContext;

/**
 * Class: VehicleCollectionManagerImpl
 * implements the VehicleCollectionManager contract.
 */
@Path(VehicleCollectionManagerImpl.VEHICLES_RESOURCE)
public class VehicleCollectionManagerImpl implements VehicleCollectionManager {

    public static final String USERNAME_PARAM = "{username}";

    public static final String VEHICLES_RESOURCE = "/users/" + USERNAME_PARAM
            + "/vehicles";
    
    private static final String VEHICLE_NAME_PARAM = "{vehicleName}";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(VehicleCollectionManagerImpl.class);

    @Context
    private HttpContext context;

    private VehicleService vehicleService;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Override
    public Response register(final JAXBElement<Vehicle> vehicleRef) {
        // obtaining the username from the context path
        final String username = context.getRequest().getPathSegments().get(1)
                .getPath();
        final Vehicle vehicle = vehicleRef.getValue();
        vehicle.setOwner(username);
        try {
            vehicleService.register(vehicle);
            return Response.created(
                    URI.create("/vehicles/" + vehicle.getName())).build();
        }
        catch (final GazooException e) {
            LOGGER.error("Error when registering the vehicle", e);
            final boolean isDuplicated = e.getErrorType() == ErrorType.DUPLICATION;
            final Status statusToReturn = isDuplicated ? Status.NOT_MODIFIED
                    : Status.BAD_REQUEST;
            return Response.status(statusToReturn).build();
        }
    }

    @GET
    @Path(VEHICLE_NAME_PARAM)
    @Produces(APPLICATION_JSON)
    @Override
    public Response get(@PathParam("vehicleName") final String vehicleName) {
        final Vehicle vehicle = vehicleService.get(vehicleName);
        return Response.ok(vehicle).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Override
    public Response search(@QueryParam("make") final String make,
            @QueryParam("model") final String model,
            @QueryParam("year") final String year,
            @QueryParam("color") final String color) {
        return Response.ok(vehicleService.search(null, null, null, null)).build();
    }

    @PUT
    @Path(VEHICLE_NAME_PARAM)
    @Override
    public Response update(final JAXBElement<Vehicle> item) {
        // TODO Auto-generated method stub
        return null;
    }

    @DELETE
    @Path(VEHICLE_NAME_PARAM)
    @Override
    public Response delete(@PathParam("vehicleName") final String vehicleName) {
        try {
            vehicleService.delete(vehicleName);
            return Response.ok().build();
        }
        catch (final GazooException e) {
            LOGGER.error(String
                    .format("Error when deleting the vehicle with name %s",
                            vehicleName), e);
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    public void setVehicleService(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

}

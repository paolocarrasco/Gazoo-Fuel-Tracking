package pe.com.abaris.gazoo.ws.vehicle;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

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

    public static final String VEHICLES_RESOURCE = "/users/" + USERNAME_PARAM + "/vehicles";

    @Context
    private HttpContext context;

    private VehicleService vehicleService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response register(final JAXBElement<Vehicle> vehicleRef) {
        final String username = context.getRequest().getPathSegments().get(1).getPath();
        final Vehicle vehicle = vehicleRef.getValue();
        vehicle.setOwner(username);
        vehicleService.register(vehicle);
        return Response.created(URI.create("/vehicles/" + vehicle.getName()))
                .build();
    }

    @Override
    public Response search(final List<String> filters) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setVehicleService(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

}

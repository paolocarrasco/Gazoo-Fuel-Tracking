package pe.com.abaris.gazoo.ws.vehicle;


import java.util.List;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Interface: VehicleCollectionManager
 * is the API contract for the management of vehicle collections.
 */
public interface VehicleCollectionManager {
    /**
     * Method: register
     * creates the given vehicle.
     * 
     * Parameters:
     *  owner - is the user that owns the vehicle
     *  vehicle - is the vehicle to be registered.
     * 
     * Returns:
     *  a response with the URI of the new vehicle, if everything goes fine;
     *  otherwise, a response with the error.
     */
    Response register(JAXBElement<Vehicle> vehicle);

    /**
     * Method: search
     * lists the vehicles that matches the given filter.
     * 
     * Parameters:
     *  owner - is the user that owns the vehicles
     *  filters - is the list of restrictions for the search.
     * 
     * Returns:
     *  a response with the collection of vehicles that matches the search.
     */
    Response search(List<String> filters);
}

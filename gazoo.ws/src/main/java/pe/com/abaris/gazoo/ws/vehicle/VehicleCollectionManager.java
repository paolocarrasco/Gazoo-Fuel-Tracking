package pe.com.abaris.gazoo.ws.vehicle;


import javax.ws.rs.core.Response;

import pe.com.abaris.gazoo.model.Vehicle;
import pe.com.abaris.gazoo.ws.ResourceManager;

/**
 * Interface: VehicleCollectionManager
 * is the API contract for the management of vehicle collections.
 */
public interface VehicleCollectionManager extends ResourceManager<Vehicle> {

    /**
     * Method: search
     * lists the vehicles that matches the given filters.
     * 
     * Parameters:
     *  make - is the make of the vehicle.
     *  model - is the model of the vehicle.
     *  year - is the year of the vehicle.
     *  color - is the color of the vehicle.
     * 
     * Returns:
     *  a response with the collection of vehicles that matches the filters.
     */
    Response search(String make, String model, String year, String color);
}

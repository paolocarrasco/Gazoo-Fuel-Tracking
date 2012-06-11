package pe.com.abaris.gazoo.services;

import java.util.List;

import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Interface: VehicleService
 * is the contract for the service that contains the logic to manage vehicles.
 */
public interface VehicleService {

    /**
     * Method: register
     * creates a record for a new vehicle.
     * 
     * Parameters:
     *  vehicle - The vehicle to be recorded.
     */
    void register(Vehicle vehicle);
    
    /**
     * Method: update
     * updates the vehicle information.
     * 
     * Parameters:
     *  vehicle - The vehicle to be updated.
     */
    void update(Vehicle vehicle);
    
    /**
     * Method: delete
     * removes the information of the vehicle that matches the given ID.
     * 
     * Parameters:
     *  id - The ID of the vehicle to delete.
     */
    void delete(String id);
    
    /**
     * Method: search
     * looks for the vehicles that match the given filters.
     * 
     * Parameters:
     *  filters - The criteria of the search.
     */
    List<Vehicle> search(List<Filter> filters); 
}

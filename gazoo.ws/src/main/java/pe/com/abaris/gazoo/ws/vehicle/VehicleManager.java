package pe.com.abaris.gazoo.ws.vehicle;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Interface: VehicleManager
 * is the API contract for the management of vehicles individually.
 */
public interface VehicleManager {

    Response get(String owner, String vehicleName);
    /**
     * Method: update
     * updates the information of an existing vehicle.
     * 
     * Parameters:
     *  owner - is the user that owns the vehicle
     *  vehicle - is the vehicle to be updated.
     * 
     * Returns;
     *  a response with the status of the update.
     */
    Response update(String owner, String vehicleName, JAXBElement<Vehicle> vehicle);

    /**
     * Method: delete
     * removes the vehicle identified by the given name.
     * 
     * Parameters:
     *  owner - is the user that owns the vehicle
     *  name - is the name of the vehicle to be deleted.
     *  
     * Returns:
     *  a response with the status of the deletion.
     */
    Response delete(String owner, String name);

}

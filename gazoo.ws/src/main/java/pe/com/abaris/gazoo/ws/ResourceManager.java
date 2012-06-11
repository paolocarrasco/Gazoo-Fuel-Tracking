package pe.com.abaris.gazoo.ws;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import pe.com.abaris.gazoo.model.BaseEntity;

/**
 * Interface: BaseManager
 * defines the generic base contract for the exposed managers in the API.
 */
public interface ResourceManager<T extends BaseEntity<?>> {
    /**
     * Method: register
     * creates the given item.
     * 
     * This could be *idempotent*: if the item was created before, it will be
     * updated with this information. When implementing this method, analyze
     * what attributes gives uniqueness to an instance, and if it would be
     * necessary to makes its registration idempotent.
     * 
     * Parameters:
     *  item - is the item to be registered.
     * 
     * Returns:
     *  a response with the ID of the registered item, if everything goes fine;
     *  otherwise, a response with the error.
     */
    Response register(JAXBElement<T> item);

    /**
     * Method: update
     * updates the information of an existing item.
     * 
     * *This shouldn't be idempotent!*
     * 
     * Parameters:
     *  item - is the item to be updated.
     * 
     * Returns;
     *  a response with the status of the update.
     */
    Response update(JAXBElement<T> item);

    /**
     * Method: delete
     * removes the item identified by the given ID.
     * 
     * Parameters:
     *  id - is the ID of the item to be deleted.
     *  
     * Returns:
     *  a response with the status of the deletion.
     */
    Response delete(String id);

    /**
     * Method: search
     * lists the items that matches the given filter.
     * 
     * Parameters:
     *  filters - is the list of restrictions for the search.
     * 
     * Returns:
     *  a response with the collection of items that matches the search.
     */
    Response search(List<String> filters);
}

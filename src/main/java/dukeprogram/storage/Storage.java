package dukeprogram.storage;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.KeyNotFoundException;

/**
 * Storages stores a hashmap of data mapping a variable header name
 * to a Serializable object. Objects saved can be retrieved from and loaded
 * to this Storage object.
 * This object is intermittently saved to disk when serialize is called.
 */
public class Storage implements Serializable {

    @JsonProperty
    private HashMap<String, Serializable> dataObjectsInMemory;

    /**
     * Creates a new storage object
     */
    public Storage() {
        dataObjectsInMemory = new HashMap<>();
    }

    /**
     * Adds a new variable header associated with a
     * serializable object intended to be saved
     * @param header the header name used to save and retrieve the serializable object
     * @param obj the serializable object to store
     */
    public void put(String header, Serializable obj) {
        dataObjectsInMemory.put(header, obj);
    }

    /**
     * Retrieves from this Storage a saved Serializable object only if it exists.
     * @param header the name associated with this Serializable object
     * @return the Serializable object that was retrieved
     * @throws KeyNotFoundException if the given header name could not be identified
     */
    public Serializable get(String header) throws KeyNotFoundException {
        if (!dataObjectsInMemory.containsKey(header)) {
            throw new KeyNotFoundException(header, "dataObjectsInMemory");
        } else {
            return dataObjectsInMemory.get(header);
        }
    }

    /**
     * Do not use this method. This is reserved for Jackson serialisation procedures.
     * Accessing the hashmap may cause unexpected behaviours and saved data mutations.
     * @return the internal hashmap of the storage object
     */
    public HashMap<String, Serializable> getDataObjectsInMemory() {
        return dataObjectsInMemory;
    }
}

package dukeprogram.storage;

import exceptions.KeyNotFoundException;

import java.io.Serializable;
import java.util.HashMap;

public class Storage implements Serializable {

    private final HashMap<String, Serializable> dataObjectsInMemory;

    public Storage() {
        dataObjectsInMemory = new HashMap<>();
    }

    public void put(String header, Serializable obj) {
        dataObjectsInMemory.put(header, obj);
    }

    public Serializable get(String header) throws KeyNotFoundException {
        if (!dataObjectsInMemory.containsKey(header)) {
            throw new KeyNotFoundException(header, "dataObjectsInMemory");
        } else {
            return dataObjectsInMemory.get(header);
        }
    }
}

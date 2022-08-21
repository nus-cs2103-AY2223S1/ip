package DukeProgram;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.HashMap;

public class DataInMemory implements Serializable {
    private final HashMap<String, Serializable> dataObjectsInMemory;

    public DataInMemory() {
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

package managers;

import exceptions.DukeException;
import storage.Storage;
import storage.StorageType;
import storage.TaskStorage;

import java.util.HashMap;

/**
 * Fictional storage manager that encapsulates the storage of data in a file
 *
 * @author Emily Ong Hui Qi
 */
public class StorageManager {
    //
    private final HashMap<StorageType, ? extends Storage> storages;

    public StorageManager() throws DukeException {
        this.storages = new HashMap<>(){{
            put(StorageType.TASK, new TaskStorage());
        }};

        for (Storage storage : this.storages.values()) {
            storage.initialize();
        }
    }

    public TaskStorage getTaskStorage() {
        return (TaskStorage) this.storages.get(StorageType.TASK);
    }
}

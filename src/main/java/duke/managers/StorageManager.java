package duke.managers;

import java.util.HashMap;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.StorageType;
import duke.storage.TaskStorage;

/**
 * Fictional storage manager that encapsulates the storage of data in a file
 *
 * @author Emily Ong Hui Qi
 */
public class StorageManager {
    private final HashMap<StorageType, ? extends Storage> storages;

    /**
     * TODO: Add JavaDocs
     */
    public StorageManager() throws DukeException {
        this.storages = new HashMap<>() {
            {
                put(StorageType.TASK, new TaskStorage());
            }
        };

        for (Storage storage : this.storages.values()) {
            storage.initialize();
        }
    }

    public TaskStorage getTaskStorage() {
        return (TaskStorage) this.storages.get(StorageType.TASK);
    }
}

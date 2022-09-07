package duke.managers;

import java.util.HashMap;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.StorageType;
import duke.storage.TaskStorage;

/**
 * Encapsulates a fictional storage manager that contains the logic for managing the various {@link Storage storages}
 * used in the application.
 *
 * @author Emily Ong Hui Qi
 */
public class StorageManager {
    private final HashMap<StorageType, ? extends Storage> storages;

    /**
     * Sets up the {@link Storage storages} used in the application and initializes each of them.
     *
     * @throws DukeException If an error occurred when initializing a particular storage.
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

    /**
     * Returns the initialized {@link TaskStorage} object used in the application.
     *
     * @return The initialized {@link TaskStorage} object used in the application.
     */
    public TaskStorage getTaskStorage() {
        return (TaskStorage) this.storages.get(StorageType.TASK);
    }
}

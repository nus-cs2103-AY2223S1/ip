package duke.storage;

import java.io.File;
import java.io.IOException;

import duke.exceptions.DukeException;

/**
 * Encapsulates the logic for managing data in files.
 *
 * @author Emily Ong Hui Qi
 */
public abstract class Storage {
    protected static final String ERROR_STORAGE_NOT_INITIALIZED = "Storage is not initialized yet!";
    private static final String ERROR_CREATING_DATA_FOLDER = "No permission to create data folder!";
    private static final String ERROR_CREATING_DATA_FILE = "No permission to create data file!";

    private static final String ASSERTION_FILE_STORAGE_INITIALIZED = "File storage should be initialized";

    private static final String FILE_DIRECTORY = "data";

    private final File storage;
    private boolean isInitialized;

    /**
     * Sets up the storage object for the provided file name.
     *
     * @param dataFilename The file name specifying where the data should be stored.
     */
    public Storage(String dataFilename) {
        this.storage = new File(String.format("%s/%s", Storage.FILE_DIRECTORY, dataFilename));
        this.isInitialized = false;
    }

    protected File getStorage() throws DukeException {
        if (!this.isInitialized) {
            throw new DukeException(Storage.ERROR_STORAGE_NOT_INITIALIZED);
        }

        assert this.storage != null : Storage.ASSERTION_FILE_STORAGE_INITIALIZED;
        return this.storage;
    }

    /**
     * Initializes the storage by retrieving the file belonging to the file path for the
     * data, creating the relevant files and folders if necessary.
     *
     * @throws DukeException If the files or folders for the data cannot be retrieved or created.
     */
    public void initialize() throws DukeException {
        File parentFolder = this.storage.getParentFile();

        // Checks if the folder specified exists or not
        try {
            if (!parentFolder.exists() && !parentFolder.mkdir()) {
                throw new DukeException(Storage.ERROR_CREATING_DATA_FOLDER);
            }
        } catch (SecurityException e) {
            throw new DukeException(Storage.ERROR_CREATING_DATA_FOLDER);
        }
        // Checks if the file specified exists or not
        try {
            if (!this.storage.exists() && !this.storage.createNewFile()) {
                throw new DukeException(Storage.ERROR_CREATING_DATA_FILE);
            }
        } catch (IOException | SecurityException e) {
            throw new DukeException(Storage.ERROR_CREATING_DATA_FILE);
        }

        // The storage has been successfully initialized
        this.isInitialized = true;
    }
}

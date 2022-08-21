package database;

import exceptions.DukeException;

import java.io.File;
import java.io.IOException;

public class Database {
    private static final String FILE_DIRECTORY = "data";

    protected static final String ERROR_DATABASE_NOT_INITIALIZED = "Database is not initialized yet!";

    private static final String ERROR_CREATING_DATA_FOLDER = "No permission to create data folder!";
    private static final String ERROR_CREATING_DATA_FILE = "No permission to create data file!";

    private final File database;
    private boolean isInitialized;

    public Database(String dataFilename) {
        this.database = new File(String.format("%s/%s", Database.FILE_DIRECTORY, dataFilename));
        this.isInitialized = false;
    }

    protected File getDatabase() throws DukeException {
        if (!this.isInitialized) {
            throw new DukeException(Database.ERROR_DATABASE_NOT_INITIALIZED);
        }
        return this.database;
    }

    /**
     * Initializes the database by retrieving the file belonging to the file path for the
     * data, creating the relevant files and folders if necessary.
     *
     * @throws DukeException If the files or folders for the data cannot be retrieved or created
     */
    public void initialize() throws DukeException {
        File parentFolder = this.database.getParentFile();

        // Checks if the folder specified exists or not
        try {
            if (!parentFolder.exists() && !parentFolder.mkdir()) {
                throw new DukeException(Database.ERROR_CREATING_DATA_FOLDER);
            }
        } catch (SecurityException e) {
            throw new DukeException(Database.ERROR_CREATING_DATA_FOLDER);
        }
        // Checks if the file specified exists or not
        try {
            if (!this.database.exists() && !this.database.createNewFile()) {
                throw new DukeException(Database.ERROR_CREATING_DATA_FILE);
            }
        } catch (IOException | SecurityException e) {
            throw new DukeException(Database.ERROR_CREATING_DATA_FILE);
        }

        // The database has been successfully initialized
        this.isInitialized = true;
    }
}

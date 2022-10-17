package duke.exception;

/**
 * The StorageFileNotFoundException class represents a DukeException
 * that is thrown when the data storage file cannot be found.
 *
 * @author Edric Yeo
 */
public class StorageFileNotFoundException extends DukeException {
    /**
     * Constructor for a StorageFileNotFoundException.
     */
    public StorageFileNotFoundException() {
        super("There is no storage file found!");
    }
}

package duke.exceptions;

/**
 * Represents a type of exception that occurs when duke fails to read or write to local file.
 */
public class StorageException extends DukeException {

    /**
     * Constructs a StorageException instance with no parameter.
     */
    public StorageException() {
        super();
    }

    /**
     * Constructs a StorageException instance with an error message as String.
     *
     * @param string Error message.
     */
    public StorageException(String string) {
        super(string);
    }

}

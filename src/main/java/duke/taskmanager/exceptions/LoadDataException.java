package duke.taskmanager.exceptions;

/**
 * Exception that is thrown when there is a problem when saving data.
 */
public class LoadDataException extends Exception {
    /**
     * Exception that handles a problem when saving data.
     */
    public LoadDataException() {
        super("I am having some trouble loading the data!\n");
    }
}

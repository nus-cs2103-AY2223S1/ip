package pixel.util;

/**
 * Exception to be thrown if the user is adding a duplicate entry to the list of tasks
 */
public class DuplicateEntryException extends RuntimeException {

    /**
     * Constructor for a new DuplicateEntryException object
     *
     * @param errorMessage description of the exception
     */
    public DuplicateEntryException(String errorMessage) {
        super(errorMessage);
    }
}

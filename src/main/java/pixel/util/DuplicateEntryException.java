package pixel.util;

/**
 * Exception to be thrown if the user is adding a duplicate entry to the list of tasks
 */
public class DuplicateEntryException extends RuntimeException {

    private final String errorMessage;

    /**
     * Constructor for a new DuplicateEntryException object
     *
     * @param errorMessage description of the exception
     */
    public DuplicateEntryException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * toString method of the exception
     *
     * @return description of the exception without the package name
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}

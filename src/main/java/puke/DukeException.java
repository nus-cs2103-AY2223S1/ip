package puke;
public class DukeException extends Exception {

    private String errorMessage;

    /**
     * Creates an exception with an errormessage
     * @param errorMessage message to be associated with this exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * @return String representation of the error
     */
    @Override
    public String toString() {
        return errorMessage;
    }
} 

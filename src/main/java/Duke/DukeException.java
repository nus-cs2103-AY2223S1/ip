package Duke;

/**
 * Exception to handle special cases
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException
     *
     * @param errorMessage ro be printed
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

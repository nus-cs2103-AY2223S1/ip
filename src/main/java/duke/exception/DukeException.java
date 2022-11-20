package duke.exception;

/**
 * The exceptions thrown by Duke due to invalid inputs that Duke does not recognise.
 *
 * @author Nephelite
 * @version 0.1
 */
public class DukeException extends Exception {
    /**
     * Constructor for a DukeException
     *
     * @param errorMessage message to display after an apology
     * @since 0.1
     */
    public DukeException(String errorMessage) {
        super("Apologies, but " + errorMessage);
    }
}

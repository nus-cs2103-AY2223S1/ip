package duke.common;
/**
 * Custom checked exception for the duke chat bot.
 *
 * @author Tan Jun Wei
 */
public class DukeException extends Exception {
    /**
     * Constructs a duke exception.
     *
     * @param errorMessage The message to be displayed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

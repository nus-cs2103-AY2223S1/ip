package duke;
/**
 * Represents the Exception specific to the duke chatBot.
 */
public class DukeException extends Exception {
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}

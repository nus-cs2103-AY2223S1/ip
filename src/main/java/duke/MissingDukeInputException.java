package duke;

/**
 * Represents a Duke exception when the user command is missing some key information.
 */
public class MissingDukeInputException extends DukeException {
    MissingDukeInputException(String msg) {
        super(msg);
    }
}

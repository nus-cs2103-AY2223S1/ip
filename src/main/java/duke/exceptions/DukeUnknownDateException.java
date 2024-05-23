package duke.exceptions;

/**
 * Represents a DukeUnknownDateException.
 * Occurs when the input date is of wrong format.
 */
public class DukeUnknownDateException extends DukeException {
    private static final String MSG = "The additional argument for %s is of invalid format\n";

    public DukeUnknownDateException(String command) {
        super(String.format(MSG, command));
    }
}

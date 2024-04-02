package duke.exception;

/**
 * Represents an error during parsing of input
 */
public class TimeException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "please input a time (/at for events and /by for deadlines)";
    }
}

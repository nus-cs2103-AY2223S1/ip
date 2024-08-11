package duke.exceptions;

/**
 * Represents a type of exception that occurs after duke is launched.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException instance with no parameter.
     */
    public DukeException() {
        super();
    }

    /**
     * Constructs a DukeException instance with an error message as String.
     *
     * @param str Error message.
     */
    public DukeException(String str) {
        super(str);
    }

}

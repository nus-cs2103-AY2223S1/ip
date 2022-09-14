package duke.parser.exceptions;

import duke.exception.DukeException;

/**
 * Represents an exception for invalid date formats.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for an InvalidDateException.
     * @param dateFormat The format the date should be in.
     */
    public InvalidDateException(String dateFormat) {
        super(String.format("Please specify a valid date in this format: %s!", dateFormat));
    }
}

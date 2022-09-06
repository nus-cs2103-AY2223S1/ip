package duke.common.exceptions;

import duke.common.Messages;

/**
 * Represents an exception for invalid date formats.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for an InvalidDateException.
     * @param dateFormat The format the date should be in.
     */
    public InvalidDateException(String dateFormat) {
        super(String.format(Messages.MESSAGE_INVALID_DATE_FORMAT, dateFormat));
    }
}

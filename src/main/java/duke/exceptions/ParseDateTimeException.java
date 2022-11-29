package duke.exceptions;

/**
 * Represents an Exception due to invalid datetime input and encapsulates an exception message.
 *
 * @author sikai00
 */
public class ParseDateTimeException extends Exception {
    public ParseDateTimeException(String msg) {
        super(msg);
    }
}

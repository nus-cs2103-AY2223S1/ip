package duke.exceptions;

/**
 * Represents an EmptyDateTimeException which is thrown when
 * a required date or time argument is missing.
 */
public class EmptyDateTimeException extends Exception {

    /**
     * Constructs an EmptyDateTimeException with Duke Aemon's standard message.
     */
    public EmptyDateTimeException() {
        super("You would bargain with Time, yet name neither date nor hour. Specify both.");
    }
}

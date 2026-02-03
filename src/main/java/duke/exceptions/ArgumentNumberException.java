package duke.exceptions;

/**
 * Represents an ArgumentNumberException which is thrown when a command
 * is given an incorrect number or order of arguments.
 */
public class ArgumentNumberException extends Exception {

    /**
     * Constructs an ArgumentNumberException with Duke Aemon's standard message.
     */
    public ArgumentNumberException() {
        super("You have spoken, yet not as required. Consult 'help' if clarity eludes you.");
    }

    /**
     * Constructs an ArgumentNumberException with a custom message.
     *
     * @param msg Custom exception message
     */
    public ArgumentNumberException(String msg) {
        super(msg);
    }
}

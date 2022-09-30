package duke.exceptions;

/**
 * Represents an ArgumentNumberException class which is inherited from the Exception class and
 * occurs when the user enters an incorrect number or order of arguments for a command
 */
public class ArgumentNumberException extends Exception {

    /**
     * Constructs an ArgumentExceptionException with standard message
     */
    public ArgumentNumberException() {
        super("Please input the correct number of arguments for each command. Type 'help' to see all commands.");
    }

    /**
     * Constructs an ArgumentExceptionException with custom message
     *
     * @param msg String representing custom message
     */
    public ArgumentNumberException(String msg) {
        super(msg);
    }
}

package duke.excecption;

/**
 * Class for EmptyInputException.
 */
public class EmptyInputException extends RuntimeException {
    /**
     * Constructor for EmptyInputException.
     */
    public EmptyInputException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}

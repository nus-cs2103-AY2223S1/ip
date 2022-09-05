package duke;

/**
 * Error to be thrown if a command that is not recognised is given
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

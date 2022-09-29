package duke;

/*
 * DukeException is a class that extends Exception and is thrown when there is an error in the program.
 * When the program is run, it will catch the exception and print the error message.
 */
public class DukeException extends Exception {
    String message;

    /*
     * Constructor for DukeException.
     * 
     * @param message The error message to be printed.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
        assert (this.message != null);
    }

    /*
     * Returns the error message.
     * 
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return message;
    }
}

package duke;

/**
 * DukeException represents the list of exceptions encountered by Duke
 */
public class DukeException extends Exception{

    /**
     * Constructor of DukeException class.
     * Sets the message of DukeException to the local variable.
     *
     * @param message The message for the DukeException.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String border = "##############################################";
        return border + "\n" + super.getMessage() + "\n" + border;
    }
}

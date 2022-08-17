/**
 * DukeException takes in errors of the user input.
 */
public class DukeException extends Exception {

    /**
     * Constructor of a DukeException object.
     * @param message         Error message.
     */
    public DukeException(String message) {
        super(message);
    }

    public String toString() {
        return "Oh no! " + super.getMessage();
    }

}

/**
 * DukeExceptions represent errors in user input when using Alfred.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return " ☹ Apologies! " + super.getMessage() + "\n";
    }
}

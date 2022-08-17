/**
 * The DukeException class represents all possible exceptions
 * that can occur during user's interaction with chatbot.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "\t" + super.getMessage();
    }
}

package duke.exception;

public class InvalidInput extends DukeException {
    public InvalidInput(String message) {
        super("Invalid Input! " + message);
    }
}

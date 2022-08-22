package DukeException;

public class InvalidInput extends DukeException {
    public InvalidInput(String message) {
        super("Invalid Input! " + message);
    }
}

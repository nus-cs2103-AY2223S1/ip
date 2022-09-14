package monkeexceptions;

public class InvalidValueException extends MonkeException {
    public InvalidValueException() {
        super("No such task exists, please enter a valid value.");
    }
}

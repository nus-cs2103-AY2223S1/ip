package monkeExceptions;

public class NoValueException extends MonkeException {
    public NoValueException(String command) {
        super(command + " must be followed by a value, please enter a value.");
    }
}

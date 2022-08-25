package alanExceptions;

public class NoValueException extends AlanException {
    public NoValueException(String command) {
        super(command + " must be followed by a value, please enter a value.");
    }
}

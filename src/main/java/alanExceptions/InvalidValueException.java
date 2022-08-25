package alanExceptions;

public class InvalidValueException extends AlanException {
    public InvalidValueException() {
        super("No such task exists, please enter a valid value.");
    }
}

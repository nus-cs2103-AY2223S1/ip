package alanExceptions;

public class NoDescriptionException extends AlanException {
    public NoDescriptionException(String command) {
        super(command + " must be followed by a description, please enter a description.");
    }
}

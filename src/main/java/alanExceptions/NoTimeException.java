package alanExceptions;

public class NoTimeException extends AlanException {
    public NoTimeException(String command) {
        super(command + " must be followed by a time, please enter a time.");
    }
}

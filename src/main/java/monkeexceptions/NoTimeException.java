package monkeExceptions;

public class NoTimeException extends MonkeException {
    public NoTimeException(String command) {
        super(command + " must be followed by a time, please enter a time.");
    }
}

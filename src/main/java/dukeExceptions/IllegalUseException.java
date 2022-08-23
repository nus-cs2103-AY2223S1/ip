package dukeExceptions;

public class IllegalUseException extends DukeException {
    public IllegalUseException(String s) {
        super(String.format("%s should not be used and should be overridden!", s));
    }
}

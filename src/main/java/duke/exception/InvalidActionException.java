package duke.exception;

public class InvalidActionException extends CompileException {
    public InvalidActionException(String action) {
        super("I don't know what [" + action + "] means :-(");
    }
}

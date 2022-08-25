package duke.exception;

public class InvalidActionException extends CompileException {
    public InvalidActionException(String action) {
        super("I don't know what [" + action + "] means :-(");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InvalidActionException) {
            InvalidActionException e = (InvalidActionException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}

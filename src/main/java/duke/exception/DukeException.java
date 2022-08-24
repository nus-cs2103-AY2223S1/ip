package duke.exception;

abstract public class DukeException extends Exception {
    public String message;

    public DukeException(String message) {
        super(":( OOPS!! " + message);
    }

    @Override
    public String toString() {
        return ":( OOPS!! " + this.message;
    }
}

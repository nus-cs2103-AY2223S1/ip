package duke.exception;

public abstract class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        super(":( OOPS!! " + message);
    }

    @Override
    public String toString() {
        return ":( OOPS!! " + this.message;
    }
}

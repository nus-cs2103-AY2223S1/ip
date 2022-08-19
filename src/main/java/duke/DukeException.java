package duke;

public class DukeException extends Exception {
    public DukeException(String message, Throwable error) {
        super(message, error);
    }

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Oh no! " + super.getMessage();
    }
}

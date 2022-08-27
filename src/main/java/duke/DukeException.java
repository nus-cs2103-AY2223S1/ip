package duke;

public class DukeException extends RuntimeException {
    private final String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "DukeException: " + message;
    }
}

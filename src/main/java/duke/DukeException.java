package duke;

public class DukeException extends RuntimeException {
    private String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    public String toString() {
        return message;
    }
}

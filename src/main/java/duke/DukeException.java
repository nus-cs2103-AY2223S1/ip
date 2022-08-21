package duke;

public class DukeException extends Exception {
    private final String message;
    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    @Override
    public String toString() {
        return this.getMessage();
    }
}


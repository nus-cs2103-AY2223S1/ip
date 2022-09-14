package duke;

public class DukeException extends Exception {
    String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Oops! " + message;
    }
}

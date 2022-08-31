package duke;

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}

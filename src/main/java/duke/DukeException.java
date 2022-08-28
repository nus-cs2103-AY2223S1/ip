package duke;

public abstract class DukeException {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}

package duke;

public class DukeException extends Exception {
    public DukeException(String errormessage) {
        super(errormessage);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}

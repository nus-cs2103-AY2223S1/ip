package duke.exceptions;

public class DukeException extends Exception {

    public DukeException(String e) {
        super(e);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}

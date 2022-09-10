package duke.exceptions;

public class OutOfRangeException extends DukeException {

    @Override
    public String toString() {
        return "No such task exists.";
    }
}

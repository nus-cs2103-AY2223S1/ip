public class DukeDeadlineEmptyException extends DukeException {
    DukeDeadlineEmptyException() {
        super();
    }
    public String toString() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }
}

package duke.exceptions;

public class NoBeforeException extends DukeException {

    @Override
    public String toString() {
        return "OOPS!!! Please enter the correct due date format d/mm/yyyy [HHmm]";
    }
}

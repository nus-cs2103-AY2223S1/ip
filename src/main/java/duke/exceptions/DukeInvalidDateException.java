package duke.exceptions;

public class DukeInvalidDateException extends DukeException {

    public DukeInvalidDateException() {
        super("An invalid date has been entered. duke.tasks.Date should be formatted as YYYY-MM-DD");
    }
}

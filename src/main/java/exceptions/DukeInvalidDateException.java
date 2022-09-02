package exceptions;

public class DukeInvalidDateException extends DukeException {

    public DukeInvalidDateException() {
        super("An invalid date has been entered. tasks.Date should be formatted as YYYY-MM-DD");
    }
}

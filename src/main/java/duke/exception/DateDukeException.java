package duke.exception;

public class DateDukeException extends DukeException {
    public DateDukeException() {
        super("Aiyoh! Please fill in the date in this format yyyy-mm-dd");
    }
}

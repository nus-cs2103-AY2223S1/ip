package duke;

public class InvalidDateException extends DukeException {

    public InvalidDateException() {
        super("Invalid date format, please enter date in yyyy-mm-dd format.");
    }
}

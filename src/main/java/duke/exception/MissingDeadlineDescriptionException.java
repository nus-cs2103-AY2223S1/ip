package duke.exception;

public class MissingDeadlineDescriptionException extends DukeException {

    public MissingDeadlineDescriptionException() {
        super("OOPS!!! The deadline description is missing the deadline\n "
                + "FORMAT: deadline <deadline description> /by <yyyy-MM-dd HHmm / yyyy-MM-dd> ");
    }
}

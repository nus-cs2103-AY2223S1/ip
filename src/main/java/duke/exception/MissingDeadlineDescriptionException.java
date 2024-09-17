package duke.exception;

public class MissingDeadlineDescriptionException extends DukeException {

    public MissingDeadlineDescriptionException() {
        super("The deadline description is missing the deadline, I can't store this in my hole\n "
                + "FORMAT: deadline <deadline description> /by <yyyy-MM-dd HHmm / yyyy-MM-dd> ");
    }
}

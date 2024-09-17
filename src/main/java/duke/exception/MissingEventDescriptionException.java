package duke.exception;

public class MissingEventDescriptionException extends DukeException {

    public MissingEventDescriptionException() {
        super("The event description is missing the event duration, I can't store this in my hole\n "
                + "FORMAT: event <event description> /at <event duration> ");
    }
}

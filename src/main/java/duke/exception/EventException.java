package duke.exception;

public class EventException extends DukeException {
    public EventException() {
        super("The description and time of event cannot be empty");
    }

    public EventException(String error) {
        super(error);
    }
}

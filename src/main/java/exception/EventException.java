package exception;

public class EventException extends CommandException {
    public EventException() {
        super("The description and time of event cannot be empty");
    }
}

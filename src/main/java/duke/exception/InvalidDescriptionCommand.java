package duke.exception;

public class InvalidDescriptionCommand extends DukeException{
    private static final String eventString = "Description of event must be followed by /at then followed by time of " +
            "event.";
    private static final String deadlineString = "Description of deadline must be followed by /by then followed by " +
            "time of deadline.";

    public InvalidDescriptionCommand(boolean isEvent) {
        super(isEvent ? eventString : deadlineString);
    }
}

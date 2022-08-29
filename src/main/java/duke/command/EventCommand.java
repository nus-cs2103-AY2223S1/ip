package duke.command;

//import exception
import duke.exception.UnexpectedDateTimeFormatException;

//import task
import duke.task.Event;

/**
 * Represents EventCommand object to be called when user inputs 'event'.
 */
public class EventCommand extends TaskCommand {
    public static final String COMMAND_WORD = "event";

    /**
     * Constructs Event object with description, date and time.
     *
     * @param description description of task.
     * @param dateTime date and time when the task should be done at.
     * @throws UnexpectedDateTimeFormatException when date and time is not in dd/MM/yyyy HHmm format.
     */
    public EventCommand(String description, String dateTime) throws UnexpectedDateTimeFormatException {
        super(new Event(description, dateTime));
    }
}

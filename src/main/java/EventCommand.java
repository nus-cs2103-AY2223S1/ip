/**
 * This class encapsulates an event command from the user.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    EventCommand(TaskList taskList, Event event) {
        super(taskList, event);
    }
}

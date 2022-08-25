package duke.command;

import duke.task.Event;

/**
 * This command encapsulates an Event object and inserts it into
 * the task list when executed.
 */
public class EventCommand extends TaskCommand {

    /** The keyword to run EventCommand. */
    public static final String COMMAND_NAME = "event";

    /**
     * Constructs a EventCommand object encapsulating the specified parameter.
     *
     * @param newEvent the specified Event parameter.
     */
    public EventCommand(Event newEvent) {
        super(newEvent);
    }
}

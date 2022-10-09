package duke.command;

import duke.task.Event;
import duke.task.TaskList;

/**
 * This class encapsulates an event command from the user.
 */
public class EventCommand extends AddCommand {
    // Solution below adapted from https://github.com/teikjun/duke
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_SEPARATOR = "/at";

    /**
     * Creates an EventCommand with the given TaskList and Event.
     *
     * @param taskList The TaskList.
     * @param event    The Event.
     */
    public EventCommand(TaskList taskList, Event event) {
        super(taskList, event);
    }
}

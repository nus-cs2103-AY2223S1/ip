package duke.commands;

import duke.task.Task;

/**
 * Represents a command that adds an event to the task list.
 */
public class EventCommand extends AddCommand {

    public static final String COMMAND_WORD = "event";

    /**
     * Constructs a new EventCommand instance.
     *
     * @param taskToAdd Task to be added to the task list.
     */
    public EventCommand(Task taskToAdd) {
        super(taskToAdd);
    }
}

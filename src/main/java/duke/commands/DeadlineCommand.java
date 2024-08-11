package duke.commands;

import duke.task.Task;

/**
 * Represents a command that adds a deadline to the task list.
 */
public class DeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";

    /**
     * Constructs a new DeadlineCommand instance.
     *
     * @param taskToAdd Task to be added to the task list.
     */
    public DeadlineCommand(Task taskToAdd) {
        super(taskToAdd);
    }

}

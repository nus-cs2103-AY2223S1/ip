package duke.commands;

import duke.task.Task;

/**
 * Represents a command that adds a todo to the task list.
 */
public class ToDoCommand extends AddCommand {

    public static final String COMMAND_WORD = "todo";

    /**
     * Constructs a new ToDoCommand instance.
     *
     * @param taskToAdd Task to be added to the task list.
     */
    public ToDoCommand(Task taskToAdd) {
        super(taskToAdd);
    }

}

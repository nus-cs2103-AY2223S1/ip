package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to print the task list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * {@inheritDoc}
     * This command prints the task list to the user.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.printList();
    }

}

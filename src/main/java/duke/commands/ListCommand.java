package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Represents a command that list all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list:\n";

    /**
     * Executes the command by showing the users of the tasks stored in the task list.
     *
     * @param tasks Task List that stores tasks.
     * @param storage Storage in charge of writing to the local disk.
     * @return A string showing a message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String successMessage = MESSAGE_SUCCESS + tasks.iterate();
        return successMessage;
    }

    /**
     * Keeps the programme running.
     *
     * @return True.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}

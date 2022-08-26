package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that list all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list:";

    /**
     * Executes the command by showing the users of the tasks stored in the task list.
     *
     * @param tasks Task List that stores tasks.
     * @param ui Ui that sends message to the user.
     * @param storage Storage in charge of writing to the local disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSuccessMessage(MESSAGE_SUCCESS);
        tasks.iterate();
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

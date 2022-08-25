package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to delete a specific task from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int toDelete;

    /**
     * Constructor for the DeleteCommand.
     * @param toDelete The index of the task to be deleted.
     */
    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete - 1;
    }

    /**
     * Delete the specific task in the task list and inform the user that the task
     * has been deleted.
     * @param task The TaskList object of the chatbot.
     * @param ui The Ui object of the chatbot.
     * @param storage The storage object of the chatbot.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        try {
            Task taskToDelete = task.getTask(toDelete);
            task.deleteTask(toDelete);
            ui.displayDeleteTask(taskToDelete);
        } catch (IndexOutOfBoundsException e) {
            ui.displayInvalidTaskIndex();
        }
    }

    /**
     * Return true if the command is exit command.
     * @return Return true if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

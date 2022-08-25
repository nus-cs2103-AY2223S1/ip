package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a specific task as not done in the task list.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private int toUnmark;

    /**
     * Constructor for the UnmarkCommand Object.
     * @param toUnmark The index of the task to be mark as not done.
     */
    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark - 1;
    }

    /**
     * Mark the specific task as not done in the task list and inform the user that the task
     * has been mark as not done.
     * @param task The TaskList object of the chatbot.
     * @param ui The Ui object of the chatbot.
     * @param storage The storage object of the chatbot.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        try {
            task.unmarkTask(toUnmark);
            Task taskToUnmark = task.getTask(toUnmark);
            ui.displayUnmarkTask(taskToUnmark);
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

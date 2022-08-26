package duke.commands;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 * It deletes the task based on the provided index of the task in the list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Noted. I've removed this duke.task:\n ";
    private int index;

    /**
     * Constructs a new DeleteCommand instance.
     *
     * @param index Index of the task in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by deleting the task with given index in the task list.
     * Writes the updated result to the local disk.
     * Tells the user if the provided index is invalid.
     *
     * @param tasks Task List that stores tasks.
     * @param ui Ui that sends message to the user.
     * @param storage Storage in charge of writing to the local disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.findTask(this.index);
            tasks.delete(task);
            String successMessage = MESSAGE_SUCCESS + task.toString()
                    + "\n" + tasks.getCount();
            ui.showSuccessMessage(successMessage);
            storage.overwriteFile(tasks, ui);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("☹ Please enter an index in the range!");
        }
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

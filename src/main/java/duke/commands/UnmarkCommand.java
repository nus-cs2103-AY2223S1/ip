package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command that indicates a task as not done yet.
 * It unmarks the task based on the provided index of the task in the task list.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = "OK, I've marked this duke.task as not done yet:\n ";
    private int index;

    /**
     * Constructs a new UnmarkCommand instance.
     *
     * @param index Index of the task in the task list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by indicating the task with the given index
     * stored in the task list as not done yet.
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
            task.markAsNotDone();
            String successMessage = MESSAGE_SUCCESS + task.toString();
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

package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a specific task as done in the task list.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int toMark;

    /**
     * Constructor for the MarkCommand Object.
     * @param toMark The index of the task to be mark as done.
     */
    public MarkCommand(int toMark) {
        this.toMark = toMark - 1;
    }

    /**
     * Mark the specific task as done in the task list and inform the user that the task
     * has been mark as done.
     * @param task The TaskList object of the chatbot.
     * @param ui The Ui object of the chatbot.
     * @param storage The storage object of the chatbot.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        try {
            task.markTask(this.toMark);
            Task taskToMark = task.getTask(toMark);
            ui.displayMarkTask(taskToMark);
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

package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = "OK, I've marked this duke.task as not done yet:\n ";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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
    @Override
    public boolean isRunning() {
        return true;
    }
}

package commands;

import storage.Storage;
import storage.TaskList;
import task.Task;
import ui.Ui;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n ";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.findTask(this.index);
            task.markAsDone();
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

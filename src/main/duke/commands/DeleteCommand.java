package commands;

import others.DukeException;
import storage.Storage;
import storage.TaskList;
import task.Task;
import ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Noted. I've removed this task:\n ";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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

    @Override
    public boolean isRunning() {
        return true;
    }
}

package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int toDelete;

    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete - 1;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}

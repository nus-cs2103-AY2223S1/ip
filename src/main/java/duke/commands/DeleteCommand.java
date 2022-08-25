package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {
    public static final String DELETE_COMMAND = "delete";

    public static final String MESSAGE_SUCCESS = "Yay, successfully removed this unwanted task :P \n"
            + "%1$s" + "\n"
            + "Remaining " + "%2$s" + " tasks in the list.\n";

    private int taskNumber;
    private Task toDelete;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(List tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(taskNumber);
            storage.save();
            toDelete = tasks.getTask(taskNumber);
            ui.showToUser(String.format(MESSAGE_SUCCESS, toDelete, tasks.numberOfTasks()));
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

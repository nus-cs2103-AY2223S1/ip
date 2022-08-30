package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Stores the index of the task to be deleted from TaskList.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * @param number 1 based indexing for the task to be deleted.
     */
    public DeleteCommand(int number) {
        this.indexToDelete = number - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Task deleted: " + tasks.remove(indexToDelete);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

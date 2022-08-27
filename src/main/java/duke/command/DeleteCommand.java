package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents the command for the deleting tasks from Duke's TaskList.
 */
public class DeleteCommand implements Command{

    private final int toDelete;

    /**
     * Constructs a DeleteCommand.
     *
     * @param toDelete the index of the task to be deleted in Duke's TaskList
     */
    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete;
    }

    /**
     * Executes the delete command and prints the results of this delete command using Duke's Ui.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has
     * @param ui The Ui created when starting Duke
     * @param storage The Storage created when starting Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.delete(toDelete));
        storage.refresh(tasks);
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

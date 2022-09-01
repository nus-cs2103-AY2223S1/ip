package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command for the deleting tasks from Duke's TaskList.
 */
public class DeleteCommand implements Command{

    private final int TO_DELETE;

    /**
     * Constructs a DeleteCommand.
     *
     * @param TO_DELETE Index of the task to be deleted in Duke's TaskList.
     */
    public DeleteCommand(int TO_DELETE) {
        this.TO_DELETE = TO_DELETE;
    }

    /**
     * Executes the delete command and prints the results of this delete command using Duke's Ui.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has
     * @param ui Ui created when starting Duke.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.delete(TO_DELETE));
        storage.refresh(tasks);
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return False since this is not an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command for asking Duke to list its tasks in the TaskList.
 */
public class ListCommand implements Command {

    /**
     * Executes the command by retrieving the list from Duke's TaskList and printing it.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param ui Ui created when starting Duke.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.toPrintFormat());
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

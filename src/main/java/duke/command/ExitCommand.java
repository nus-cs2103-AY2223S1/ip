package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command for the exiting Duke.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by closing Duke's Ui and printing the exit message.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has
     * @param ui The Ui created when starting Duke
     * @param storage The Storage created when starting Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.close();
        ui.showBye();
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return true since this is an exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

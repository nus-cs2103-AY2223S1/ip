package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents the command for the exiting Duke.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by closing Duke's Ui and printing the exit message.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.getByeMessage();
    }
}

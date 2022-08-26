package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the command of exiting the programme.
 *
 * @author Sun Ruoxin
 */
public class ExitCommand extends Command {
    /**
     * Executes the command.
     * Show the feedback to the user.
     *
     * @param tasks the list of tasks
     * @param ui the UI
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitMessage();
    }

    /**
     * Returns a boolean value representing whether to exit the programme
     * after the command is executed.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

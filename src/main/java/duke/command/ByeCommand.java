package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the application terminating command that is executed when the user inputs <code>bye</code>.
 *
 * @author njxue
 * @version v0.1
 */
public class ByeCommand extends Command {
    /**
     * Executes the <code>bye</code> command. Terminates the current Duke session.
     * 
     * @param tasks <code>TaskList</code> containing the list of tasks.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true, because <code>bye</code> is an application terminating command.
     * 
     * @return True, terminating the Duke application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the application terminating command that is executed when the user inputs bye.
 *
 * @author njxue
 * @version v0.1
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command. Terminates the current Duke session.
     *
     * @param tasks TaskList containing the list of tasks.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true, because bye is an application terminating command.
     *
     * @return True, terminating the Duke application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

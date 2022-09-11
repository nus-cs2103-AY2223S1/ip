package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exits the program
 */
public class ByeCommand extends Command {

    /**
     * Determines if the command should end the program for the user
     *
     * @return true by default
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exits the program
     *
     * @param tasks the list of tasks
     * @param ui the ui used to display the bye message
     * @param storage the local storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showBye();
    }
}

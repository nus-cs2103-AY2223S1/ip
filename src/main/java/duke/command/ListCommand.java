package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Displays the list of tasks in the local storage
 */
public class ListCommand extends Command {

    /**
     * Determines if the command should end the program for the user
     *
     * @return false by default
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Displays the list of tasks in the local storage using the ui
     *
     * @param tasks the list of tasks
     * @param ui the ui used to display messages to the user
     * @param storage the local storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList();
    }
}

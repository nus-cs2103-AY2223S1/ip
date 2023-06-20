package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to exit the application
 *
 * @author Pontakorn Prasertsuk
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand
     *
     * @param taskList not being used
     * @param ui the user interface to be used
     * @param storage not being used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showOutput("Goodbye!");

        return "Goodbye!";
    }

    /**
     * Returns true as this is the exit command
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

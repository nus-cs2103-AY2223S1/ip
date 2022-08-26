package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command the exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes a Command.
     *
     * @param taskList a list of tasks
     * @param storage a location to store the task information
     * @param ui an ui to handle user interactions
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        toggleIsExit();
    }
}

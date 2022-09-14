package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A class that handles the exit command.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }

    /**
     * Ensures that the program does not exit.
     *
     * @return boolean indicating not exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.storage.Storage;

/**
 * This class encapsulates an Exit Command
 */
public class ExitCommand extends Command {

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printExit();
    }
}

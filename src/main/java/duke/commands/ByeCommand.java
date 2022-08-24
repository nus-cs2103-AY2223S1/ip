package duke.commands;

import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to exit the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Checks if the Command is a ByeCommand.
     * @return True.
     */
    @Override
    public boolean isBye() {
        return true;
    }

    /**
     * Displays the goodbye message to the user.
     * @param taskList List of tasks.
     * @param ui Shows the goodbye message to the user.
     * @param storage Saves the modified list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}

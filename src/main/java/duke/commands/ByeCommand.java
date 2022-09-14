package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the command to exit the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Returns the goodbye message to be shown to the user.
     * @param taskList List of tasks.
     * @param ui Shows the goodbye message to the user.
     * @param storage Saves the modified list of tasks.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }
}

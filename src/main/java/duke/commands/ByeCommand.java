package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a bye command in Duke.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Prints goodbye message to the console.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The ui class to get the command response.
     * @param storage The storage used to save the tasks in the local file.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return Ui.GOODBYE_MESSAGE;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package duke.command;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ByeCommand class represents a bye Command given by user.
 */
public class ByeCommand extends Command {

    /**
     * The goodbye message is being returned.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of command messages.
     * @return The goodbye message.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        return ui.goodBye();
    }

    /**
     * Returns the message that this command cannot be undone.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of command messages.
     * @return Message that this command cannot be undone.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        return "There are no more changes to be undone!";
    }
}


package duke.command;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * WrongCommand class represents wrong command given by the user.
 */
public class WrongCommand extends Command {

    /**
     * Returns the message that the command is invalid.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that command is invalid.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        String message = "Please enter some valid Command";
        return ui.displayCommandMessage(message, null, null);
    }

    /**
     * Returns message that this command cannot be undone.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that the wrong command cannot be executed.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList, CommandHistory commandHistory) {
        return "This cannot be undone!";
    }
}

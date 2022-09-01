package duke.command;

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
     * @return The message that command is invalid.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        String message = "Please enter some valid Command";
        return ui.displayCommandMessage(message, null, null);
    }
}

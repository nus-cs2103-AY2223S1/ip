package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command when the user input is invalid
 */
public class InvalidCommand extends Command {

    /**
     * To execute the {@code InvalidCommand}
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the current {@code Ui}
     * @param storage the current {@code Storage}
     * @return a string to be printed onto the dialog box
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showError(new DukeException("Sorry. I don't understand your command!!!").getMessage());
    }
}

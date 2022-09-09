package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command when the user input is invalid
 */
public class InvalidCommand extends Command{

    /**
     * To execute the {@code InvalidCommand}
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new DukeException("Sorry. I don't understand your command!!!").getMessage());
    }
}

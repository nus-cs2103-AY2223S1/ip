package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.ui.Ui;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    public static final String EXIT_COMMAND = "bye";

    public ExitCommand() {
    }

    @Override
    public String execute(List tasks, Ui ui, Storage storage) {
        try {
            storage.save();
            return ui.showGoodbyeMessage();
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean shouldExit() {
        return true;
    }

}

package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public static final String EXIT_COMMAND = "bye";

    public ExitCommand() {
    }

    @Override
    public void execute(List tasks, Ui ui, Storage storage) {
        try {
            storage.save();
            ui.showGoodbyeMessage();
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }

}

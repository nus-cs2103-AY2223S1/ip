package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * When the command is not recognised
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return errorMessage;
    }
}

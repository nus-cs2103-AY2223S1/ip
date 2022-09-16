package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Executes when the input entered is not recognized
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    /**
     * Initialises a {@link InvalidCommand} instance with the error message
     * @param errorMessage Error message to be shown to the user
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return errorMessage;
    }
}

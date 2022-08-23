package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an incorrect command.
 */
public class InvalidCommand extends Command {

    private final String errorMessage;

    /**
     * Constructor for an invalid command.
     *
     * @param errorMessage error message to be displayed to the user
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) {
        ui.showMessages(errorMessage);
    }
}

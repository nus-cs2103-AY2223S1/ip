package sus.commands;

import sus.storage.StorageFile;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Represents an incorrect command.
 */
public class InvalidCommand extends Command {

    private final String errorMessage;

    /**
     * Constructor.
     *
     * @param errorMessage error message to be displayed to the user
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        return new CommandResult(errorMessage);
    }
}

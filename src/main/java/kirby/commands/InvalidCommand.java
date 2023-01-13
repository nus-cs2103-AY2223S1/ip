package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;

/**
 * InvalidCommand class handles when the user creates an invalid command.
 */
public class InvalidCommand extends Command {
    private final String message;

    /**
     * Constructor for the class InvalidCommand.
     *
     * @param exception the exception that caused the command to be invalid.
     */
    public InvalidCommand(Exception exception) {
        this.message = exception.getMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return this.message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

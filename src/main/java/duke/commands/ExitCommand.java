package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that stops the programme.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_SUCCESS = "See you soon!";

    /**
     * Execute the command by ending the programme.
     *
     * @param tasks Task List that stores tasks.
     * @param ui Ui that sends message to the user.
     * @param storage Storage in charge of writing to the local disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSuccessMessage(MESSAGE_SUCCESS);
    }

    /**
     * Stops the programme.
     *
     * @return False.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}

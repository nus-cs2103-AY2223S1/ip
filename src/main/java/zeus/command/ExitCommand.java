package zeus.command;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;

/**
 * Class that represents a Command to exit the chat.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param taskList List of tasks
     * @param ui The Ui
     * @param storage The Storage
     * @throws ZeusException If user input is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        ui.addExitMessage();
    }

    /**
     * Returns true to show user has exited.
     * @return boolean to show user has exited.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

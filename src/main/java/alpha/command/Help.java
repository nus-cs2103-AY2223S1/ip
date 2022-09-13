package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

/**
 * Displays all the commands and their required format.
 */
public class Help extends Command {
    /**
     * {@inheritDoc}
     *
     * Returns all the recognised input commands and their expected format.
     * @return String containing list of commands and their format.
     */
    @Override
    public String execute(TaskList tasks, Ui uI, FileOperations fileOperations) {
        return uI.generateCommandExecutionMessage(this, null, 0);
    }
}

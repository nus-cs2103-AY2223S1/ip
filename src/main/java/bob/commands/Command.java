package bob.commands;

import bob.BobException;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Encapsulates all possible commands
 */
public abstract class Command {

    /**
     * Method to return whether bot should be terminated
     *
     * @return false for all commands (except ByeCommand)
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes command
     *
     * @param taskList current list of tasks
     * @param storage location where task list is stored and read
     * @param ui
     * @return response based on executed command
     * @throws BobException
     */
    public abstract String executeCommand(TaskList taskList, Storage storage, Ui ui) throws BobException;
}


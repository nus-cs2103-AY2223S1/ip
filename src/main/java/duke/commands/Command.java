package duke.commands;

import duke.exceptions.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param taskList the user's task list
     * @param ui interface to interact with the user
     * @param storage storage handler of user data
     * @throws InvalidCommandException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException;

    /**
     * {@return true if and only if this command is the exit command}
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}



















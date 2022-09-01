package duke.commands;

import duke.Response;
import duke.exceptions.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * An executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList the user's task list
     * @param storage  storage handler of user data
     * @throws InvalidCommandException
     */
    public abstract Response execute(TaskList taskList, Storage storage) throws InvalidCommandException;

    /**
     * {@return true if and only if this command is the exit command}
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}



















package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;

/**
 * Represents a user command.
 */
public abstract class Command {
    /**
     * Executes operations relating to the command.
     *
     * @param taskList Task list.
     * @param storage  Storage to interact with the save file.
     * @throws CheeseException If something is wrong with executing command.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws CheeseException;
}

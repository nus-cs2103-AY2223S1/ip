package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents an executable command.
 *
 * @author sikai00
 */
public abstract class Command {
    /**
     * Executes the command and returns its result.
     * If the command is successfully executed, a CommandResult with relevant information about execution of command
     * is returned.
     *
     * @param taskList The TaskList to use
     * @param storage The storage to use
     * @return CommandResult with relevant information about execution of command
     * @throws DukeException If the command executed encounters an issue with the parameters
     */
    public abstract CommandResult execute(TaskList taskList, Storage storage) throws DukeException;
}

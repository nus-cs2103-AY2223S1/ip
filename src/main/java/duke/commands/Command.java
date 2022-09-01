package duke.commands;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Represents a command generated from input of the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks Task List that stores tasks.
     * @param storage Storage in charge of writing to the local disk.
     * @return A string showing a message.
     * @throws DukeException If the method is invoked.
     *
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes.");
    }

    /**
     * Indicate whether the programme should continue running.
     * It is to be overridden by subclasses of Command.
     *
     * @return A boolean value.
     */
    public abstract boolean isRunning();

}

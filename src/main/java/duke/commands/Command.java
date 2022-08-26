package duke.commands;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a command generated from input of the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks Task List that stores tasks.
     * @param ui Ui that sends message to the user.
     * @param storage Storage in charge of writing to the local disk.
     * @throws DukeException If the method is invoked.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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

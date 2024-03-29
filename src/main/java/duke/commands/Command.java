package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The abstract Command class represents a command
 * entered by user.
 */
public abstract class Command {

    /**
     * Executes the Command.
     * @param tasks Specified task list.
     * @param ui Specified Ui.
     * @param storage Specified storage.
     * @throws DukeException when Command cannot be successfully executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the Command is the ByeCommand.
     * @return true only when Command is ByeCommand.
     */
    public abstract boolean isExit();
}

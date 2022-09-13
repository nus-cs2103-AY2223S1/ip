package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * An abstract class that encapsulates the Command instance
 * in Duke
 */
public abstract class Command {

    /**
     * Returns a boolean value true if the command is a bye command,
     * false otherwise.
     *
     * @return a boolean value on whether the command is a bye command
     */
    public abstract boolean isByeCommand();

    /**
     * Executes the command
     *
     * @param tasks The current list of tasks
     * @param ui The Ui instance to return the result to the user
     * @param storage The Storage instance to store the result to local storage
     * @return the string representation of the result
     * @throws DukeException if errors are encountered during execution
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}

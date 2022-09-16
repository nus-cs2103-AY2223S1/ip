package duke.commands;

import java.io.IOException;

import duke.TaskList;
import duke.exception.DukeException;
import duke.Ui;
import duke.Storage;

/**
 * The Command class encapsulates the execution of a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList List containing the current tasks.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command
     * with the exception of ListCommand.
     * @throws DukeException
     * @throws IOException
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}

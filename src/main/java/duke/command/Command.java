package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents the command that is executed when the user inputs a command.
 *
 * @author njxue
 * @version v0.1
 */
public abstract class Command {

    /**
     * Executes the corresponding command.
     *
     * @param tasks <code>TaskList</code> containing the list of tasks.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.v
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an application terminating command.
     * 
     * @return True, if the command is an application terminating command, otherwise false.
     */
    public abstract boolean isExit();
}
   

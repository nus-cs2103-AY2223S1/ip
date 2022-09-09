package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to be executed by program.
 */
public abstract class Command {

    /**
     * Runs command that will do various changes based on the command.
     *
     * @param taskList task list.
     * @param storage files storing task list.
     *
     * @return String response of Duke regarding user input
     *
     * @throws DukeException if error occurs during execution of command.
     */
    public abstract String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException;
}

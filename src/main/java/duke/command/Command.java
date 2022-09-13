package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;

/**
 * Represents a command to be executed by program.
 */
public abstract class Command {

    /**
     * Runs command that will do various changes based on the command.
     *
     * @param taskList list of tasks.
     * @param clientList list of clients.
     * @return String response of Duke regarding user input.
     * @throws DukeException if error occurs during execution of command.
     */
    public abstract String execute(TaskList taskList, ClientList clientList) throws DukeException;
}

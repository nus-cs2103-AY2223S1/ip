package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful command execution message
     * @throws DukeException if user input has wrong format
     */
    public abstract String execute(TaskList taskList) throws DukeException;

}

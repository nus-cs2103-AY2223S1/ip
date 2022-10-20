package duke.commands;

import duke.DukeException;
import duke.TaskList;

public interface Command {
    /**
     * Executes the command and returns Duke's response
     *
     * @param taskList
     * @return Duke's response
     * @throws DukeException
     */
    String execute(TaskList taskList) throws DukeException;
}

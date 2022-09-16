package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents a general command.
 */
public abstract class Command {
    /**
     * Carries out the command according to what it is supposed to do.
     *
     * @param tasks The user's task list.
     * @return The program's response to executing this command.
     * @throws DukeException if any I/O or file access exceptions are thrown.
     */
    public abstract String execute(TaskList tasks) throws DukeException;
}

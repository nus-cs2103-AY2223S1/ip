package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Class to represent different commands.
 */
public abstract class Command {
    public abstract String executeAndGetResponse(TaskList tasklist) throws DukeException;
}

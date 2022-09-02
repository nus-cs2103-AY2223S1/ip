package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.utils.Storage;

/**
 * Represents a skeleton for a Command Class.
 * @author Jason
 */
public abstract class Command {

    /**
     * Runs the command based on the command type.
     * @param taskList Tasklist to update tasks data.
     * @param storage Storage to save updates to duke.TaskList.
     * @return String message of running the Task command.
     * @throws DukeException Index out of bounds or improper syntax errors.
     * @throws IOException Input Errors.
     */
    public abstract String run(TaskList taskList, Storage storage) throws DukeException, IOException;
}

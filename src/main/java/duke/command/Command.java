package duke.command;

import duke.DukeException;
import duke.utils.Storage;
import duke.TaskList;

import java.io.IOException;

/**
 * Represents a skeleton for a Command Class.
 * @author Jason
 */
public abstract class Command {

    /**
     * Runs the command based on the command type.
     * @param taskList Tasklist to update tasks data.
     * @param storage Storage to save updates to duke.TaskList.
     * @throws DukeException Index out of bounds or improper syntax errors.
     * @throws IOException Input Errors.
     */
    public abstract void run(TaskList taskList, Storage storage) throws DukeException, IOException;
}

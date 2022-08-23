package duke.command;

import duke.DukeException;
import duke.utils.Storage;
import duke.TaskList;

import java.io.IOException;

public abstract class Command {

    /**
     * Runs the command based on the command type
     * @param taskList duke.TaskList to update tasks data
     * @param storage duke.utils.Storage to save updates to duke.TaskList
     * @throws DukeException Index out of bounds / Improper syntax errors
     * @throws IOException Input Errors
     */
    public abstract void run(TaskList taskList, Storage storage) throws DukeException, IOException;
}

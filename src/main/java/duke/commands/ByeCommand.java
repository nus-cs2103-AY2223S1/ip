package duke.commands;

import duke.TaskList;
import duke.Storage;
import duke.DukeException;

/**
 * Commands that represents the bye command.
 */
public class ByeCommand implements Command {
    /**
     * Runs the bye command by exiting the program.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        System.out.println("Bye. Hope to see you again soon!");
        storage.writeAll(tasks);
    }
}

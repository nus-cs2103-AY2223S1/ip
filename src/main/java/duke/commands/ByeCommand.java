package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Commands that represents the bye command.
 */
public class ByeCommand implements Command {
    /**
     * Runs the bye command by exiting the program.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @return String output of executing the task.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        storage.writeAll(tasks);
        String output = "";
        output += "Bye. Hope to see you again soon!\n";
        output += "Platform will close in 3 seconds...";
        return output;
    }

    @Override
    public boolean isBye() {
        return true;
    }
}

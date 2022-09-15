package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Command that represents the list command.
 */
public class ListCommand implements Command {
    /**
     * Runs the list command by listing all tasks in the tasklist.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @return String output of executing the task.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.getSize() == 0) {
            return "You dont have any tasks added.\nType help for some tasks to add!";
        }
        String output = "Here is the list of all your tasks!\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            int index = i + 1;
            output += index + ". " + tasks.get(i) + "\n";
        }
        return output;

    }

    @Override
    public boolean isBye() {
        return false;
    }
}

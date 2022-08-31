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
     * @throws DukeException If any error occur.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        for (int i = 0; i < tasks.getSize(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + tasks.get(i));
        }
    }
}

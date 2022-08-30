package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.Task;


/**
 * Class to encapsulate a command that removes Task from list.
 */
public class DeleteCommand extends Command {
    private String index;


    /**
     * Constructor for DeleteCommand.
     *
     * @param index The index of the task to be removed, in String.
     */
    public DeleteCommand(String index) {

        this.index = index;
    }

    /**
     * Delete the task from the task list.
     * Throw an Exception if index is invalid.
     *
     * @param ui The user interface.
     * @param tasks The list of tasks to remove task from.
     * @param storage The local storage file.
     *
     * @throws DukeException If index < 0 or index > number of tasks in tasklist.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(this.index);
            Task task = tasks.get(i - 1);
            String reply = tasks.removeTask(this.index);
            return reply;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(this.index, "delete");
        }

    }
}

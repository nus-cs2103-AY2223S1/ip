package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to delete a task from task list.
 *
 * @author benjytan45678
 * @version 0.1
 */
public class DeleteCommand extends Command {
    private int number;

    /**
     * Creates a command that deletes a task.
     *
     * @param number deletes specified task based on its position in the list
     */
    public DeleteCommand(int number) {

        this.number = number;
    }

    /**
     * Deletes the task from the task list and stored the updated task list in the local file
     *
     * @param tasks specified list of tasks
     * @param ui specific ui object to interact with
     * @param storage specific storage to store the updated task list
     * @throws DukeException specific error message to be thrown
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTaskList().get(this.number - 1);
            tasks.delete(this.number - 1);
            int total = tasks.totalSize();
            ui.showDelete(task, total);
            storage.store(tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OH NO! You are not allowed here");
        }

    }
}

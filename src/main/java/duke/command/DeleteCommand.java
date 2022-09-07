package duke.command;

import duke.*;
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
    public String execute(TaskList tasks, Ui ui, Storage storage, Container container, ContactMap contacts) throws DukeException {
        try {
            int originalTotal = tasks.totalSize();
            Task task = tasks.getTaskList().get(this.number - 1);
            tasks.delete(this.number - 1);
            int total = tasks.totalSize();
            assert total == (originalTotal - 1) : "there should be an decrement of total tasks by 1";
            String message = ui.showDelete(task, total);
            storage.store(tasks.getTaskList());
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OH NO! You are not allowed here");
        }

    }
}

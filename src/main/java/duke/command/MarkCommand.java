package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to mark a task as completed
 *
 * @author benjytan45678
 * @version 0.1
 */
public class MarkCommand extends Command {
    private int number;

    /**
     * Creates a command that marks a task as completed
     *
     * @param number marks specified task based on its position in the list
     */
    public MarkCommand(int number) {

        this.number = number;
    }

    /**
     * Marks the task in the task list and stored the updated task list in the local file
     *
     * @param tasks specified list of tasks
     * @param ui specific ui object to interact with
     * @param storage specific storage to store the updated task list
     * @throws DukeException specific error message to be thrown
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTaskList().get(this.number - 1);
            task.setCompleted();
            String message = ui.showMarked(task);
            storage.store(tasks.getTaskList());
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OH NO! You are not allowed here");
        }
    }
}

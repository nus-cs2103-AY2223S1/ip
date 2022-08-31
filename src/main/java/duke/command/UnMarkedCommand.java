package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to unmark a task
 *
 * @author benjytan45678
 * @version 0.1
 */
public class UnMarkedCommand extends Command {
    private int number;

    /**
     * Un-marks a task as uncompleted
     *
     * @param number un-marks specified task based on its position in the list
     */
    public UnMarkedCommand(int number) {

        this.number = number;
    }

    /**
     * Un-marks the task in the task list and stored the updated task list in the local file
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
            task.setUncompleted();
            ui.showUnMarked(task);
            storage.store(tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OH NO! You are not allowed here");
        }
    }
}

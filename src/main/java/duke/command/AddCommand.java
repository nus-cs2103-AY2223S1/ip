package duke.command;


import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to add a task to task list.
 *
 * @author benjytan45678
 * @version 0.1
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a command that adds a task.
     *
     * @param task specified task to be added to a list
     */
    public AddCommand(Task task) {

        this.task = task;
    }

    /**
     * Adds the task to the task list and stored the updated task list in the local file
     *
     * @param tasks specified list of tasks
     * @param ui specific ui object to interact with
     * @param storage specific storage to store the updated task list
     * @throws DukeException specific error message to be thrown
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        int total = tasks.totalSize();
        ui.showAdd(task, total);
        storage.store(tasks.getTaskList());
    }
}

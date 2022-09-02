package duke.command;

import duke.data.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeleteCommand class
 */
public class DeleteCommand extends Command {

    private final int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    /**
     * Delete task from task list and disk storage
     *
     * @param storage Disk storage
     * @param tasks   Task list
     * @param ui      Ui to display to users
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.deleteTask(taskToDelete);
            storage.store(tasks);
            return "Noted. I've removed this task:\n "
                            + task
                            + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            return ("Task index not found");
        }

    }
}

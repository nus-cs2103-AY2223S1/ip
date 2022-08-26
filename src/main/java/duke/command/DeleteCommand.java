package duke.command;

import duke.data.Storage;
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
     * @param tasks Task list
     * @param ui Ui to display to users
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            tasks.deleteTask(taskToDelete);
            storage.store(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task index not found");
        }

    }
}

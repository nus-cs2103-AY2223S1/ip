package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * AddCommand class to add task to tasklist.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task into the lastlist.
     *
     * @param tasks The tasks to be executed.
     * @return The UI to be shown when a new task is added
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.addTask(this.task);
        try {
            Storage.save(tasks);
        } catch (Exception e) {
            return Ui.showError(e);
        }
        return Ui.showAddTaskMessage(this.task, tasks.getSize());
    }
}

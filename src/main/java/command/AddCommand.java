package command;

import duke.Storage;
import task.Task;
import task.TaskList;
import duke.Ui;

/**
 * This class handles all commands related to adding tasks
 * and inherits from the Command class.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for the AddCommand class.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * Adds a task to the tasklist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        String str = ui.displayTask(ui.ADDED, task);
        str += ui.showTotalTasks(taskList);
        boolean status = storage.saveData(taskList);
        return status ? str : str + "\nError saving";
    }
}

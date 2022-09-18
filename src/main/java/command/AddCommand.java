package command;

import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;
import task.Task;

/**
 * This class encapsulates a command asking Koba to add a task to tasklist.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs a AddCommand, given a Task to be added.
     *
     * @param task The Task to be added to the TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Method that adds the Task to the TaskList.
     *
     * @param tasklist The TaskList to add the task into.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.saveTask(task);
        storage.saveToFile(tasklist);
        return ui.addTasktoString(task, tasklist);
    }
}

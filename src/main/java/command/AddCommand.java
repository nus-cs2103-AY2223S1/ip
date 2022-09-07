package command;

import myduke.Storage;
import myduke.TaskList;
import myduke.Ui;
import task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for a AddCommand instance, given a Task to be added.
     *
     * @param task The Task to be added to the TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Method that adds the Task to the TaskList.
     *
     * @param tasklist The TaskList instance for the task manager.
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

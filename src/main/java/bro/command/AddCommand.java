package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;
import bro.task.Task;

/**
 * Adds command to the tasklist.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor of the AddCommand class.
     * @param task Gives the task to the given task variable.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     *
     * Adds the given task to the file.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        String type = this.task.getTaskType();
        switch (type) {
        case ("bro.task.Todo"):
            return tasklist.todoTask(this.task, storage);
        case ("bro.task.Deadline"):
            return tasklist.deadlineTask(this.task, storage);
        case ("bro.task.Event"):
            return tasklist.eventTask(this.task, storage);
        default:
            return "Exception";
        }
    }
}

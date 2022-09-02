package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;
import bro.task.Task;

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
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        String type = this.task.getTaskType();
        switch (type) {
        case ("bro.task.Todo"):
           tasklist.todoTask(this.task, storage);
           break;
        case ("bro.task.Deadline"):
           tasklist.deadlineTask(this.task, storage);
           break;
        case ("bro.task.Event"):
           tasklist.eventTask(this.task, storage);
           break;
        }
    }
}
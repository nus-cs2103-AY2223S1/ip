package commands;

import data.Task;
import data.TaskList;
import storage.Storage;

/**
 * Command to create a new task.
 */
public abstract class NewTaskCommand extends Command {
    private final Task task;

    /**
     * Returns a new command to add task to a list.
     *
     * @param task New task to be added.
     */
    public NewTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.add(task);
        storage.save(tasks);
        return "Got it. I've added this task: \n"
                + "  " + task + "\n"
                + "Now you have " + tasks.getSize() + " task" + (tasks.getSize() == 1 ? "" : "s") + " in the list.";
    }
}

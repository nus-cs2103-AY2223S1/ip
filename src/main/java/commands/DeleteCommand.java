package commands;

import data.Task;
import data.TaskList;
import storage.Storage;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.delete(Integer.parseInt(description) - 1);
        storage.save(tasks);
        return "Noted. I've removed this task: \n"
                + "  " + task + "\n"
                + "Now you have " + tasks.getSize() + " task" + (tasks.getSize() == 1 ? "" : "s") + " in the list.";
    }
}

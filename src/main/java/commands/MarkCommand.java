package commands;

import data.Task;
import data.TaskList;
import storage.Storage;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String description;

    public MarkCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.markDone(Integer.parseInt(description) - 1);
        storage.save(tasks);
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }
}

package commands;

import data.TaskList;
import storage.Storage;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}

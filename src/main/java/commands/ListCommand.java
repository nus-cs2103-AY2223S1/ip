package commands;

import data.TaskList;
import storage.Storage;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}

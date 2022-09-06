package betago.commands;

import betago.Storage;
import betago.TaskList;

public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        return tasks.listItems();
    }
}

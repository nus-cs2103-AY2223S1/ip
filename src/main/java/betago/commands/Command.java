package betago.commands;

import betago.Storage;
import betago.TaskList;

public interface Command {
    String execute(TaskList tasks, Storage storage, String str);
}

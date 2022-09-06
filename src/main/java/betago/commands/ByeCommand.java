package betago.commands;

import betago.Storage;
import betago.TaskList;

public class ByeCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        return "Goodbye Human. Till next time.\n";
    }
}

package betago.commands;

import betago.Storage;
import betago.TaskList;

public class InvalidCommand implements Command {
    private String message;
    public InvalidCommand(String message) {
        this.message = message;
    }
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        return this.message;
    }
}

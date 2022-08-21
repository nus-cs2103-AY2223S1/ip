package commands;

import managers.TaskManager;

public class IncorrectCommand implements Command {
    private static final String UNKNOWN_COMMAND_ERROR = "I do not understand your command!";

    @Override
    public String execute(TaskManager taskManager) {
        return IncorrectCommand.UNKNOWN_COMMAND_ERROR;
    }
}

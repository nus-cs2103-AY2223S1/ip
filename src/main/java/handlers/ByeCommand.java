package handlers;

import models.TaskManager;

public class ByeCommand implements DukeCommand{
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public String execute(TaskManager taskManager, String arguments) {
        return ByeCommand.GOODBYE_MESSAGE;
    }
}

package handlers;

import models.TaskManager;

public class ListTasksCommand implements DukeCommand {
    @Override
    public String execute(TaskManager taskManager, String arguments) {
        return taskManager.toString();
    }
}

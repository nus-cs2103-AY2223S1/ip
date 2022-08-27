package chatbot.commands;

import chatbot.main.UI;
import chatbot.tasks.TaskList;

public class List implements Command {
    public static final List LIST = new List();

    @Override
    public void execute(TaskList todos, UI ui) {
        ui.list(todos.listTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

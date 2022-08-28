package chatbot.commands;

import chatbot.ui.UI;
import chatbot.tasks.TaskList;

public class List implements Command {
    public static final List LIST = new List();

    @Override
    public void execute(TaskList todos, UI ui) {
        ui.listAll(todos.listTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package chatbot.commands;

import chatbot.ui.UI;
import chatbot.tasks.TaskList;

/**
 * Represents the command to be executed by the chatbot which lists
 * all the tasks in the todo list.
 */
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

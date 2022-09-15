package chatbot.commands;

import chatbot.tasks.TaskList;
import chatbot.ui.Response;
import chatbot.ui.UI;

/**
 * Represents the command to be executed by the chatbot which lists
 * all the tags in the todo list.
 */
public class ListTags implements Command {
    public static final ListTags LIST_TAGS = new ListTags();

    @Override
    public void execute(TaskList todos, UI ui) {
        ui.listAllTags(todos.listTags());
    }

    @Override
    public String execute(TaskList todos, Response resp) {
        return resp.listAllTags(todos.listTags());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

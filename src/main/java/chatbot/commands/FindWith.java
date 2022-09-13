package chatbot.commands;

import chatbot.tasks.TaskList;
import chatbot.ui.Response;
import chatbot.ui.UI;

/**
 * Represents the command to be executed by the chatbot which lists
 * all the tasks with the given tag.
 */
public class FindWith implements Command {
    private String tag;

    public FindWith(String tag) {
        this.tag = tag;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        ui.listTaskOn(todos.getTaskWithTag(this.tag));
    }

    @Override
    public String execute(TaskList todos, Response resp) {
        return resp.listTaskWith(todos.getTaskWithTag(this.tag));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

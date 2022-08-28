package chatbot.commands;

import chatbot.tasks.TaskList;
import chatbot.ui.UI;

/**
 * Represents the command to be executed by the chatbot which find
 * all tasks that contains the relevant keyword.
 */
public class Find implements Command {
    private String keyword;

    public Find(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        ui.listFound(todos.find(this.keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

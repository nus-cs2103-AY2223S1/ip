package chatbot.commands;

import chatbot.tasks.TaskList;
import chatbot.ui.UI;

public class Find implements Command {
    private final String keyword;

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

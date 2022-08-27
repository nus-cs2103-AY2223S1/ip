package chatbot.commands;

import chatbot.ui.UI;
import chatbot.tasks.TaskList;

public interface Command {
    void execute(TaskList todos, UI ui);
    boolean isExit();
}

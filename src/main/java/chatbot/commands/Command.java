package chatbot.commands;

import chatbot.main.UI;
import chatbot.tasks.TaskList;

public interface Command {
    void execute(TaskList todos, UI ui);
    boolean isExit();
}

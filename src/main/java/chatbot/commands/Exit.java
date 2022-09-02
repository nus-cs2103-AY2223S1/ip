package chatbot.commands;

import chatbot.tasks.TaskList;
import chatbot.ui.UI;

/**
 * Represents the command to be executed by the chatbot which signals the end
 * of the current chatbot session.
 */
public class Exit implements Command {
    public static final Exit EXIT = new Exit();

    @Override
    public void execute(TaskList todos, UI ui) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

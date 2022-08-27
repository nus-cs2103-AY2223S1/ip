package chatbot.commands;

import chatbot.main.UI;
import chatbot.tasks.TaskList;

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

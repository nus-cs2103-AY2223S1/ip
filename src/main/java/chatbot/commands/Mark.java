package chatbot.commands;

import chatbot.main.DukeException;
import chatbot.main.UI;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

public class Mark implements Command {
    private int target;

    public Mark(int target) {
        this.target = target;
    }
    @Override
    public void execute(TaskList todos, UI ui) {
        try {
            Task marked = todos.markTask(this.target);
            ui.mark(marked);
        } catch (DukeException e) {
            ui.reprimand(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package chatbot.commands;

import chatbot.exceptions.DukeException;
import chatbot.ui.UI;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

public class Unmark implements Command {
    private int target;

    public Unmark(int target) {
        this.target = target;
    }
    @Override
    public void execute(TaskList todos, UI ui) {
        try {
            Task unmarked = todos.unmarkTask(this.target);
            ui.unmark(unmarked);
        } catch (DukeException e) {
            ui.reprimand(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

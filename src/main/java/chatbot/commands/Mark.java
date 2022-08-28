package chatbot.commands;

import chatbot.exceptions.DukeException;
import chatbot.ui.UI;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

/**
 * Represents the command to be executed by the chatbot which marks
 * the specified task in the todo list as completed.
 */
public class Mark implements Command {
    private final int target;

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

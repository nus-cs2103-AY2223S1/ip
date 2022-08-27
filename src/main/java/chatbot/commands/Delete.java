package chatbot.commands;

import chatbot.main.DukeException;
import chatbot.main.UI;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

public class Delete implements Command {
    private int target;

    public Delete(int target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        try {
            Task deleted = todos.deleteTask(this.target);
            ui.delete(deleted, todos.getNumberOfTasks());
        } catch (DukeException e) {
            ui.reprimand(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

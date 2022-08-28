package chatbot.commands;

import chatbot.exceptions.DukeException;
import chatbot.ui.UI;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

/**
 * Represents the command to be executed by the chatbot which deletes
 * the specified task from the todo list.
 */
public class Delete implements Command {
    private final int target;

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

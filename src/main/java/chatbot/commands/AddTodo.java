package chatbot.commands;

import chatbot.ui.UI;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

/**
 * Represents the command to be executed by the chatbot which adds
 * an Todo type task to the todo list.
 */
public class AddTodo implements Command {
    public String taskName;

    public AddTodo(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        Task todo = todos.addTodo(this.taskName);
        ui.add(todo, todos.getNumberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

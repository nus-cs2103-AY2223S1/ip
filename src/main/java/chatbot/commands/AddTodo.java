package chatbot.commands;

import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.Response;
import chatbot.ui.UI;

/**
 * Represents the command to be executed by the chatbot which adds
 * an Todo type task to the todo list.
 */
public class AddTodo implements Command {
    private String taskName;

    public AddTodo(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        Task todo = todos.addTodo(this.taskName);
        ui.add(todo, todos.getNumberOfTasks());
    }

    @Override
    public String execute(TaskList todos, Response resp) {
        Task todo = todos.addTodo(this.taskName);
        return resp.add(todo, todos.getNumberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

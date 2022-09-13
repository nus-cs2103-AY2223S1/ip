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
    private String[] tags;

    /**
     * The constructor of the AddTodo command.
     *
     * @param taskName
     * @param tags
     */
    public AddTodo(String taskName, String[] tags) {
        this.taskName = taskName;
        this.tags = tags;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        Task todo = todos.addTodo(this.taskName, this.tags);
        ui.add(todo, todos.getNumberOfTasks());
    }

    @Override
    public String execute(TaskList todos, Response resp) {
        Task todo = todos.addTodo(this.taskName, this.tags);
        return resp.add(todo, todos.getNumberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

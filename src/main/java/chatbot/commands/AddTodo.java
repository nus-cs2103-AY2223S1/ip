package chatbot.commands;

import chatbot.main.UI;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;

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

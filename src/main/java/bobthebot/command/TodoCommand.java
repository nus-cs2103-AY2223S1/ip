package bobthebot.command;

import bobthebot.tasks.Task;
import bobthebot.tasks.ToDoList;
import bobthebot.tasks.Todo;
import bobthebot.utils.Ui;

public class TodoCommand extends Command {
    private String taskName;
    private ToDoList list;

    public TodoCommand(String taskName, ToDoList list) {
        super("todo");
        this.taskName = taskName;
        this.list = list;
    }

    @Override
    public String execute() {
        Task todo = new Todo(taskName);
        list.addTask(todo);
        return Ui.taskAddedMessage(todo, list);
    }
}

package bobthebot.command;

import bobthebot.tasks.Task;
import bobthebot.tasks.ToDoList;
import bobthebot.tasks.Todo;
import bobthebot.utils.Ui;

/**
 * Todo Command class representing Todo command executed by the user.
 */
public class TodoCommand extends Command {
    private String taskName;
    private ToDoList list;

    /**
     * Constructs Todo command.
     */
    public TodoCommand(String taskName, ToDoList list) {
        super("todo");
        this.taskName = taskName;
        this.list = list;
    }

    /**
     * Executes the todo command by creating a new todo instance and adding it to the list.
     *      Gives the user information about the todo added.
     *
     * @return String representing the information about the added Todo.
     */
    @Override
    public String execute() {
        Task todo = new Todo(taskName);
        list.addTask(todo);
        return Ui.taskAddedMessage(todo, list);
    }
}

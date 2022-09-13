package duke.command;

import duke.oop.Storage;
import duke.oop.TaskList;
import duke.task.Todo;
import duke.oop.Ui;

public class AddTodoCommand extends Command{

    private String todoTask;

    public AddTodoCommand(String todoTask) {
        this.todoTask = todoTask;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Todo newTodo = new Todo(todoTask);
        taskList.getTasks().add(newTodo);
        storage.update(ui.listAllItems(taskList.getTasks()));
        return "Got it. I've added this task:\n" + newTodo
                + "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";

    }
}

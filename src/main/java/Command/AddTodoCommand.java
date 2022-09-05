package Command;

import duke.Storage;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

public class AddTodoCommand extends Command{

    private String todoTask;

    public AddTodoCommand(String todoTask) {
        this.todoTask = todoTask;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Todo newTodo = new Todo(todoTask);
        taskList.getTasks().add(newTodo);
        return "\"Got it. I've added this task:\" + \"\\n\" + tasks.get(numOfTasks - 1).toString()\n" +
                "                \"\\n\" + \"Now you have \" + numOfTasks + \" tasks in the list.\"";
    }
}

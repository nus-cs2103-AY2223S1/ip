package command;

import duke.TaskList;
import duke.Ui;
import task.Todo;

/**
 *  A class which encapsulates the todo command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class TodoCommand extends Command {
    String todoInput;
    TaskList currList;

    public TodoCommand(String todoInput, TaskList currList) {
        this.todoInput = todoInput;
        this.currList = currList;
    }

    /**
     * Executes the todo command and shows the user the task added.
     * @return Duke's response which is the todo task to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        String todoDesc = todoInput.split("todo ")[1];
        Todo todoTask = new Todo(todoDesc);
        currList.addTask(todoTask, false);
        String result = Ui.addTaskMessage(todoTask);
        result += Ui.getTaskNumberMessage(currList);
        return result;
    }
}

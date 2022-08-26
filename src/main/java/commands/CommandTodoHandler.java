package commands;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

public class CommandTodoHandler extends CommandHandler {

    public CommandTodoHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (value != null && flag == null && additionalValue == null) {
            return;
        }
        throw new DukeException("Correct usage: todo read book");
    }

    public String handle(TaskList taskList) {
        Task task = new ToDo(this.value);
        return taskList.addTask(task);
    }
}

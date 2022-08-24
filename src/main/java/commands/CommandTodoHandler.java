package commands;

import exceptions.DukeException;
import tasks.TaskList;

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

    public String handle(TaskList taskList) throws DukeException {
        return taskList.addToDo(value, flag, additionalValue);
    }
}

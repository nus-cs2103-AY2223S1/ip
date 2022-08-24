package commands;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;

public class CommandMarkHandler extends CommandHandler {

    public CommandMarkHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (flag == null && additionalValue == null) {
            return;
        }
        throw new DukeException("Correct usage: mark 1");
    }

    public String handle(TaskList taskList) throws DukeException {
        Task task = taskList.markTask(value, true);
        String message = "Nice! I've marked this task as done:\n\t";
        return message + task;
    }
}

package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class CommandUnmarkHandler extends CommandHandler {

    public CommandUnmarkHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (flag == null && additionalValue == null) {
            return;
        }
        throw new DukeException("Correct usage: unmark 1");
    }

    public String handle(TaskList taskList) throws DukeException {
        Task task = taskList.markTask(value, false);
        String message = "Ok, I've marked this task as not done yet:\n\t";
        return message + task;
    }
}

package commands;

import exceptions.DukeException;
import tasks.TaskList;

public class CommandDeadlineHandler extends CommandHandler {

    public CommandDeadlineHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (value != null && flag.equals("by") && additionalValue != null) {
            return;
        }
        throw new DukeException("Correct usage: deadline return book /by today");
    }

    public String handle(TaskList taskList) throws DukeException {
        return taskList.addDeadline(value, flag, additionalValue);
    }
}

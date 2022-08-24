package commands;

import exceptions.DukeException;
import tasks.TaskList;

public class CommandEventHandler extends CommandHandler {

    public CommandEventHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (value != null && flag.equals("at") && additionalValue != null) {
            return;
        }

        throw new DukeException("Correct usage: event dinner /at 6pm tomorrow");
    }

    public String handle(TaskList taskList) throws DukeException {
        return taskList.addEvent(value, flag, additionalValue);
    }
}

package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class CommandDeleteHandler extends CommandHandler {

    public CommandDeleteHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (flag == null && additionalValue == null) {
            return;
        }
        throw new DukeException("Correct usage: delete 1");
    }

    public String handle(TaskList taskList) throws DukeException {
        return taskList.deleteTask(value);
    }
}

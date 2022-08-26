package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class CommandListHandler extends CommandHandler {

    public CommandListHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (value == null && flag == null && additionalValue == null) {
            return;
        }
        throw new DukeException("Correct usage: list");
    }

    public String handle(TaskList taskList) {
        return taskList.toString();
    }
}

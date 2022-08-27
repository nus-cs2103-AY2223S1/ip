package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;


/**
 * Handler for the Delete command.
 */
public class CommandDeleteHandler extends CommandHandler {

    public CommandDeleteHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    /**
     * Checks the validity of the syntax after being parsed by CommandParser.
     * @throws DukeException error message.
     */
    public void checkValid() throws DukeException {
        if (flag == null && additionalValue == null) {
            return;
        }
        throw new DukeException("Correct usage: delete 1");
    }

    /**
     * Handles the execution of the delete command inputted by the user.
     * @param taskList the taskList to be modified.
     * @return string representation of the delete command.
     */
    public String handle(TaskList taskList) throws DukeException {
        return taskList.deleteTask(value);
    }
}

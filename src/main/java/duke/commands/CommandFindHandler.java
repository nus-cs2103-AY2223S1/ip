package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Handler the Find command.
 */
public class CommandFindHandler extends CommandHandler {

    public CommandFindHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    /**
     * Handles the execution of the find command inputted by the user.
     * @param taskList the taskList to be modified.
     * @return string representation of the find command.
     */
    public String handle(TaskList taskList) {
        return taskList.findTask(value);
    }

    /**
     * Checks the validity of the syntax after being parsed by CommandParser.
     * @throws DukeException error message.
     */
    public void checkValid() throws DukeException {
        if (value != null && flag == null && additionalValue == null) {
            return;
        }
        throw new DukeException("Correct usage: find book");
    }
}

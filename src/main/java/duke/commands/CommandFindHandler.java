package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidSyntaxException;
import duke.tasks.TaskList;

/**
 * Describes the handler the Find command.
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
        boolean isValid = value != null && flag == null && additionalValue == null;
        if (isValid) {
            assert !value.contains("|");
            return;
        }
        throw new InvalidSyntaxException("Correct usage: find book");
    }
}

package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidSyntaxException;
import duke.tasks.TaskList;

/**
 * Describes the handler for the List command.
 */
public class CommandListHandler extends CommandHandler {

    public CommandListHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    /**
     * Checks the validity of the syntax after being parsed by CommandParser.
     * @throws DukeException error message.
     */
    public void checkValid() throws DukeException {
        if (value == null && flag == null && additionalValue == null) {
            return;
        }
        throw new InvalidSyntaxException("Correct usage: list");
    }

    public String handle(TaskList taskList) {
        return taskList.toString();
    }
}

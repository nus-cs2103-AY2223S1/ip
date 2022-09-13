package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Describes the handler for the bye command.
 */
public class CommandByeHandler extends CommandHandler {

    public CommandByeHandler(String value, String flag, String additionalValue) throws DukeException {
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
        throw new DukeException("Correct usage: bye");
    }

    /**
     * Handles the execution of the bye command by setting the taskList to close.
     * @param taskList the taskList.
     * @return the reply to the user before exiting the program.
     */
    public String handle(TaskList taskList) {
        taskList.close();
        return "Bye! See you again soon!";
    }
}

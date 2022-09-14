package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidSyntaxException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Describes the handler for the Mark command.
 */
public class CommandMarkHandler extends CommandHandler {

    public CommandMarkHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    /**
     * Checks the validity of the syntax after being parsed by CommandParser.
     * @throws DukeException error message.
     */
    public void checkValid() throws DukeException {
        boolean isValid = flag == null && additionalValue == null;
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("Please input a number!\n"
                    + "Correct usage: mark 1");
        }
        if (isValid) {
            return;
        }
        throw new InvalidSyntaxException("Correct usage: mark 1");
    }

    /**
     * Handles the execution of the mark command inputted by the user.
     * @param taskList the taskList to be modified.
     * @return string representation of the mark command.
     * @throws DukeException error message.
     */
    public String handle(TaskList taskList) throws DukeException {
        Task task = taskList.markTask(value, true);
        String message = "Nice! I've marked this task as done:\n\t";
        return message + task;
    }
}

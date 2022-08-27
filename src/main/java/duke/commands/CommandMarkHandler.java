package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Handler for the Mark command.
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
        if (flag == null && additionalValue == null) {
            return;
        }
        throw new DukeException("Correct usage: mark 1");
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

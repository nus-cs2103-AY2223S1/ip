package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Handler for the Unmark command.
 */
public class CommandUnmarkHandler extends CommandHandler {

    public CommandUnmarkHandler(String value, String flag, String additionalValue) throws DukeException {
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
        throw new DukeException("Correct usage: unmark 1");
    }

    /**
     * Handles the execution of the unmark command inputted by the user.
     * @param taskList the taskList to be modified.
     * @return string representation of the unmark command.
     * @throws DukeException error message.
     */
    public String handle(TaskList taskList) throws DukeException {
        Task task = taskList.markTask(value, false);
        String message = "Ok, I've marked this task as not done yet:\n\t";
        return message + task;
    }
}

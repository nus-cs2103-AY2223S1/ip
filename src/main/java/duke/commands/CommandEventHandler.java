package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateTime;


/**
 * Handler for the Event command.
 */
public class CommandEventHandler extends CommandHandler {

    public CommandEventHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    /**
     * Checks the validity of the syntax after being parsed by CommandParser.
     * @throws DukeException error message.
     */
    public void checkValid() throws DukeException {
        if (value == null || !flag.equals("at") || additionalValue == null) {
            throw new DukeException("Correct usage: event dinner /at 24/04/2019 1600");
        }
        try {
            LocalDateTime.parse(this.additionalValue, DateTime.formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input a valid date!");
        }
    }

    /**
     * Handles the execution of the event command inputted by the user.
     * @param taskList the taskList to be modified.
     * @return string representation of the event command.
     */
    public String handle(TaskList taskList) {
        Task task = new Event(value, additionalValue);
        return taskList.addTask(task);
    }
}

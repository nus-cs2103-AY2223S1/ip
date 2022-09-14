package duke.commands;

import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidSyntaxException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateTime;

/**
 * Describes the handler for the Deadline command.
 */
public class CommandDeadlineHandler extends CommandHandler {

    public CommandDeadlineHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    /**
     * Checks the validity of the syntax after being parsed by CommandParser.
     * @throws DukeException error message.
     */
    public void checkValid() throws DukeException {
        if (value == null || !flag.equals("by") || additionalValue == null) {
            throw new InvalidSyntaxException("Correct usage: deadline return book /by 24/04/2019 1600");
        }
        assert !value.contains("|");
        assert !additionalValue.contains("|");
        try {
            DateTime.FORMATTER.parse(this.additionalValue);
        } catch (DateTimeParseException e) {
            throw new InvalidSyntaxException("Please input a valid date!\n"
                    + "Correct usage: deadline return book /by 24/04/2019 1600");
        }
    }

    /**
     * Handles the execution of the deadline command inputted by the user.
     * @param taskList the taskList to be modified.
     * @return string representation of the deadline command.
     */
    public String handle(TaskList taskList) {
        Task task = new Deadline(value, additionalValue);
        return taskList.addTask(task);
    }
}

package commands;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import utils.DateTime;

import java.time.format.DateTimeParseException;

public class CommandDeadlineHandler extends CommandHandler {

    public CommandDeadlineHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (value != null && flag.equals("by") && additionalValue != null) {
            return;
        }
        try {
            DateTime.formatter.parse(this.additionalValue);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input a valid date!");
        }
        throw new DukeException("Correct usage: deadline return book /by today");
    }

    public String handle(TaskList taskList) {
        Task task = new Deadline(value, additionalValue);
        return taskList.addTask(task);
    }
}

package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.DateTime;

import java.time.format.DateTimeParseException;

public class CommandDeadlineHandler extends CommandHandler {

    public CommandDeadlineHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (value == null || !flag.equals("by") || additionalValue == null) {
            throw new DukeException("Correct usage: deadline return book /by today");
        }
        try {
            DateTime.formatter.parse(this.additionalValue);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input a valid date!");
        }
    }

    public String handle(TaskList taskList) {
        Task task = new Deadline(value, additionalValue);
        return taskList.addTask(task);
    }
}

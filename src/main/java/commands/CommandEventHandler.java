package commands;

import exceptions.DukeException;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import utils.DateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class CommandEventHandler extends CommandHandler {

    public CommandEventHandler(String value, String flag, String additionalValue) throws DukeException {
        super(value, flag, additionalValue);
    }

    public void checkValid() throws DukeException {
        if (value != null && flag.equals("at") && additionalValue != null) {
            return;
        }
        try {
            LocalDateTime.parse(this.additionalValue, DateTime.formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input a valid date!");
        }

        throw new DukeException("Correct usage: event dinner /at 6pm tomorrow");
    }

    public String handle(TaskList taskList) {
        Task task = new Event(value, additionalValue);
        return taskList.addTask(task);
    }
}

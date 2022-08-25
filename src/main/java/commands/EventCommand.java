package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import task.Event;
import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Creates a new event
 */
public class EventCommand extends Command {
    private final String[] inputStrings;

    public EventCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.inputStrings.length == 1) {
            throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] eventStrings = inputStrings[1].split(" /at ", 2);
        if (eventStrings.length == 1 || eventStrings[1].equals("")) {
            throw new DukeException("     ☹ OOPS!!! The date/time of an event cannot be empty.");
        }
        try {
            Event event = new Event(eventStrings[0], false, LocalDateTime.parse(eventStrings[1],
                    Task.dateTimeParser));
            tasks.addTask(event);

            ui.showAddTask(event, tasks.size());
        } catch (
                DateTimeParseException exception) {
            throw new DukeException("     ☹ OOPS!!! The datetime specified is invalid, it should have the format "
                    + Task.DATE_TIME_INPUT_FORMAT);
        }
    }

    public boolean isExit() {
        return false;
    }
}

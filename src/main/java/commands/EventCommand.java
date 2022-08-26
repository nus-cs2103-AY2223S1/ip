package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import exceptions.EmptyDateTimeException;
import exceptions.EmptyDescriptionException;
import exceptions.InvalidDateTimeException;
import task.Event;
import task.Task;
import task.TaskType;

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
            throw new EmptyDescriptionException(TaskType.E);
        }
        String[] eventStrings = inputStrings[1].split(" /at ", 2);
        if (eventStrings.length == 1 || eventStrings[1].equals("")) {
            throw new EmptyDateTimeException(TaskType.E);
        }
        try {
            Event event = new Event(eventStrings[0], false, LocalDateTime.parse(eventStrings[1],
                    Task.dateTimeParser));
            tasks.addTask(event);

            ui.showAddTask(event, tasks.size());
        } catch (
                DateTimeParseException exception) {
            throw new InvalidDateTimeException(Task.DATE_TIME_INPUT_FORMAT);
        }
    }

    public boolean isExit() {
        return false;
    }
}

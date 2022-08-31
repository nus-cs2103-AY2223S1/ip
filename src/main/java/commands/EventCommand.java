package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import dukeegg.Storage;
import dukeegg.TaskList;
import dukeegg.Ui;
import exceptions.DukeException;
import exceptions.EmptyDateTimeException;
import exceptions.EmptyDescriptionException;
import exceptions.InvalidDateTimeException;
import task.Event;
import task.Task;
import task.TaskType;

/**
 * Creates a new event.
 */
public class EventCommand extends Command {
    private final String[] inputStrings;

    /**
     * Constructs an event command, which creates a new event based on input strings.
     *
     * @param inputStrings The specified input strings.
     */
    public EventCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Creates a new event and stores it in the current task list.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.inputStrings.length == 1 || this.inputStrings[1].trim().isEmpty()) {
            throw new EmptyDescriptionException(TaskType.E);
        }
        String[] eventStrings = inputStrings[1].split(" /at ", 2);
        if (eventStrings.length == 1 || eventStrings[1].trim().isEmpty()) {
            throw new EmptyDateTimeException(TaskType.E);
        }
        try {
            Event event = new Event(eventStrings[0], false, LocalDateTime.parse(eventStrings[1],
                    Task.DATE_TIME_PARSER));
            tasks.addTask(event);

            return ui.showAddTask(event, tasks.size());
        } catch (DateTimeParseException exception) {
            throw new InvalidDateTimeException(Task.DATE_TIME_INPUT_FORMAT);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}

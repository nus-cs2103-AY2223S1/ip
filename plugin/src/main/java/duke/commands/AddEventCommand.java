package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.entities.*;
import duke.enums.*;
import duke.exceptions.*;
import duke.lists.*;

/**
 * Adds Event to the tasklist
 */
public class AddEventCommand extends AddDeadlineCommand {
    public AddEventCommand(TaskList tasks, String desc, String input, String deadline) throws DukeException {
        super(tasks, desc, input, deadline);
    }

    /**
     * Add new event to the task list
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        try {
            LocalDateTime deadline = LocalDateTime.parse(this.deadline, datetime_format);
            Event current_event = new Event(descrition, deadline);
            tasks.addTask(current_event);
            wrapWithLines(Messages.ADD_EVENT.toString(), current_event.toString());
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.ERROR_INVALID_DATETIME.toString());
        }
    }
}

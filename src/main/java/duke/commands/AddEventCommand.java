package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.entities.Event;
import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Adds Event to the tasklist
 */
public class AddEventCommand extends AddDeadlineCommand {
    public AddEventCommand(TaskList tasks, String desc, String input, String deadline) throws DukeException {
        super(tasks, desc, input, deadline);
    }

    /**
     * Add new event to the task list
     * @throws DukeException when the datetime is not valid
     */
    @Override
    public void execute() throws DukeException {
        try {
            LocalDateTime deadline = LocalDateTime.parse(this.deadline, datetimeFormat);
            Event currentEvent = new Event(descrition, deadline);
            tasks.addTask(currentEvent);
            wrapWithLines(Messages.ADD_EVENT.toString(), currentEvent.toString());
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.ERROR_INVALID_DATETIME.toString());
        }
    }
}

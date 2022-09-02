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

    /**
     * Adds an event to the task list
     * @param tasks list of tasks
     * @param desc description of task to be added
     * @param input the user command
     * @param deadline the deadline of the task
     * @throws DukeException when something goes wrong
     */
    public AddEventCommand(TaskList tasks, String desc, String input, String deadline) throws DukeException {
        super(tasks, desc, input, deadline);
    }

    /**
     * Add new event to the task list
     *
     * @return wrapped message
     * @throws DukeException when the datetime is not valid
     */
    @Override
    public String execute() throws DukeException {
        try {
            LocalDateTime deadline = LocalDateTime.parse(this.deadline, datetimeFormat);
            Event currentEvent = new Event(description, deadline);
            tasks.addTask(currentEvent);
            return wrapWithoutLines(Messages.ADD_EVENT.toString(), currentEvent.toString());
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.ERROR_INVALID_DATETIME.toString());
        }
    }
}

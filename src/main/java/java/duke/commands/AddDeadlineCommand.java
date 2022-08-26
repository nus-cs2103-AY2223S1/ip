package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.entities.Deadline;
import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Adds a new deadline into the task list
 */
public class AddDeadlineCommand extends AddTodoCommand {
    protected String deadline;
    protected DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Adds a new deadline to the TaskList
     * @param tasks represents the task list
     * @param desc of the task
     * @param input the line of input given to generate the task
     * @param deadline local datetime object
     * @throws DukeException when there are missing parameters
     */
    public AddDeadlineCommand(TaskList tasks, String desc, String input, String deadline) throws DukeException {
        super(tasks, desc, input);
        if (deadline == null) {
            throw new DukeException(Messages.ERROR_MISSING_PARAMETERS.toString());
        }
        this.deadline = deadline;
    }

    /**
     * Add new event to the task list
     *
     * @return wrapped message
     * @throws DukeException when the datetime string is of the wrong format
     */
    @Override
    public String execute() throws DukeException {
        try {
            LocalDateTime deadline = LocalDateTime.parse(this.deadline, datetimeFormat);
            Deadline currentEvent = new Deadline(descrition, deadline);
            tasks.addTask(currentEvent);
            return wrapWithoutLines(Messages.ADD_DEADLINE.toString(), currentEvent.toString());
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.ERROR_INVALID_DATETIME.toString());
        }
    }
}

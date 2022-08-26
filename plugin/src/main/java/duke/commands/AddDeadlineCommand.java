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
    protected DateTimeFormatter datetime_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Adds a new deadline to the TaskList
     * @param tasks
     * @param desc
     * @param input
     * @param deadline
     * @throws DukeException
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
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        try {
            LocalDateTime deadline = LocalDateTime.parse(this.deadline, datetime_format);
            Deadline current_event = new Deadline(descrition, deadline);
            tasks.addTask(current_event);
            wrapWithLines(Messages.ADD_DEADLINE.toString(), current_event.toString());
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.ERROR_INVALID_DATETIME.toString());
        }
    }
}

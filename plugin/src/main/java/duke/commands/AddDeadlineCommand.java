package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.entities.*;
import duke.enums.*;
import duke.exceptions.*;
import duke.lists.*;

public class AddDeadlineCommand extends AddTodoCommand {
    protected String deadline;
    protected DateTimeFormatter datetime_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AddDeadlineCommand(TaskList task, String desc, String input, String deadline) throws DukeException {
        super(task, desc, input);
        if (deadline == null) {
            throw new DukeException(Messages.ERROR_MISSING_PARAMETERS.toString());
        }
        this.deadline = deadline;
    }

    /**
     * Add new event to the task list
     * 
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

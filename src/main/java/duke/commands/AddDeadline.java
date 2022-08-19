package duke.commands;

import duke.entities.*;
import duke.enums.*;
import duke.exceptions.*;
import duke.lists.*;

public class AddDeadline extends AddTodo {
    protected String deadline;

    public AddDeadline(TaskList task, String desc, String deadline) throws DukeException {
        super(task, desc);
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
        Deadline current_event = new Deadline(descrition, deadline);
        tasks.addTask(current_event);
        wrapWithLines(Messages.ADD_EVENT.toString(), current_event.toString());
    }
}

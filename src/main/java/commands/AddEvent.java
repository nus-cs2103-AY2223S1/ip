package commands;

import enums.*;
import lists.*;
import entities.*;
import exceptions.*;

public class AddEvent extends AddDeadline {

    public AddEvent(TaskList tasks, String desc, String deadline) throws DukeException {
        super(tasks, desc, deadline);
    }

    /**
     * Add new event to the task list
     * 
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        Event current_event = new Event(descrition, deadline);
        tasks.addTask(current_event);
        wrapWithLines(Messages.ADD_EVENT.toString(), current_event.toString());
    }
}

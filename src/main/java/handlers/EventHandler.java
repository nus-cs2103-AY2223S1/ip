package handlers;

import entities.Event;
import entities.Task;
import exceptions.DukeException;

import java.util.List;

import static utils.Utils.addToList;

public class EventHandler implements IHandler{
    private String eventName;
    private String flag;
    private String flagOption;

    public EventHandler(HandlerFactory factory) {
        this.eventName = factory.taskName;
        this.flag = factory.flag;
        this.flagOption = factory.flagOption;
    }

    @Override
    public void handle(List<Task> list) throws DukeException {
        if (this.eventName == null) {
            throw new DukeException("Please enter a task name!");
        }
        // TODO refactor to enum
        if (!flag.equals("at")) {
            throw new DukeException("Incorrect option flag!\nUsage:`event project meeting /at Mon 2pm`");
        }
        if (this.flagOption == null) {
            throw new DukeException("Please enter a time!");
        }
        Task event = new Event(this.eventName, this.flagOption);
        addToList(list, event);
    }
}

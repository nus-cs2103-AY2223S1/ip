package duke.handlers;

import duke.entities.Event;
import duke.entities.Task;
import duke.exceptions.DukeException;
import duke.service.Service;
import java.time.format.DateTimeParseException;

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
    public void handle(Service s) throws DukeException {
        if (this.eventName == null) {
            throw new DukeException("Please enter a task name!");
        }
        // TODO refactor to enum
        if (!flag.equals("at")) {
            throw new DukeException("Incorrect option flag!\nUsage: `event project meeting /at 2/12/2019 1800`");
        }
        if (this.flagOption == null) {
            throw new DukeException("Please enter a time!");
        }
        try {
            Task event = new Event(this.eventName, this.flagOption);
            s.addToList(event);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid Date/Time!\nUsage: `event project meeting /at 2/12/2019 1800`");
        }
    }
}

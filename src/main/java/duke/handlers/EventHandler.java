package duke.handlers;

import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.entities.Event;
import duke.entities.Task;
import duke.exceptions.DukeException;
import duke.service.Service;

/**
 * Handles User action for creating a new Event.
 */
public class EventHandler implements IHandler {
    private String eventName;
    private String flag;
    private String flagOption;

    /**
     * Constructs EventHandler from HandlerFactory
     * @param factory HandlerFactory
     */
    public EventHandler(HandlerFactory factory) {
        this.eventName = factory.getTaskName();
        this.flag = factory.getFlag();
        this.flagOption = factory.getFlagOption();
    }

    /**
     * Handles the "event" command by adding a new event to the service's tasklist.
     *
     * @param s Service object of the application
     * @throws DukeException
     */
    @Override
    public String handle(Service s) throws DukeException {
        if (this.eventName == null) {
            throw new DukeException("Please enter a task name!\nUsage: `event project meeting /at 2/12/2019 1800`");
        }
        if (this.flag == null) {
            throw new DukeException("Please enter /at time!\nUsage: `event project meeting /at 2/12/2019 1800`");
        }
        if (!flag.equals("at")) {
            throw new DukeException("Incorrect option flag!\nUsage: `event project meeting /at 2/12/2019 1800`");
        }
        if (this.flagOption == null) {
            throw new DukeException("Please enter a time!\nUsage: `event project meeting /at 2/12/2019 1800`");
        }
        try {
            Task event = new Event(this.eventName, this.flagOption);
            s.saveTasks();
            s.addToList(event);
            int size = s.getList().size();
            assert size != 0;
            return String.format("Got it. I've added this task:\n  "
                    + event
                    + "\nNow you have %d task%s in the list.", size, size != 1 ? "s" : "");
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid Date/Time!\nUsage: `event project meeting /at 2/12/2019 1800`");
        }
    }
}

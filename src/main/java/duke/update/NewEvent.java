package duke.update;

import duke.command.EventCommand;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

public class EventUpdate extends NewTask {
    private static final String DELIMITER = " /at ";
    private String[] newTaskArray;

    public EventUpdate(String[] newTaskArray) {
        this.newTaskArray = newTaskArray;
    }

    @Override
    public Task create() throws DukeException {
        if (this.newTaskArray.length < 2) {
            throw new DukeException("The description of a event cannot be empty!");
        }

        // split again to get date/time
        String[] splitArray = this.newTaskArray[1].split(EventUpdate.DELIMITER, 2);

        if (splitArray.length < 2) {
            throw new DukeException("Please enter a date and time for this event!");
        }
        // Make a new event object
        Event event = new Event(splitArray[0], splitArray[1]);

        return event;

    }
}

package duke.task;

import java.util.Date;
import duke.DukeException;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private Date timing;

    /**
     * Constructor to create new Event.
     * 
     * @param description Task description.
     * @param timing      Timing of Event.
     */
    public Event(String description, Date timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Constructor to create new Event with isDone.
     * 
     * @param description Task description.
     * @param timing      Timing of Event.
     * @param isDone      Whether the task is done or not.
     */
    public Event(String description, Date timing, boolean isDone) {
        super(description, isDone);
        this.timing = timing;
    }

    /**
     * Factory method to create new Event.
     * 
     * @param input String including task description and event timing specified
     *              after /at.
     * @return new Event.
     * @throws DukeException if event timing is not specified using /at.
     */
    public static Event createEvent(String input) throws DukeException {
        if (input.indexOf("/at ") == -1) {
            throw new DukeException("Please enter a valid event timing using the /at flag.");
        }
        if (input.indexOf("/completed ") == -1) {
            String description = input.split("/at ")[0];
            String timing = input.split("/at ")[1];
            return new Event(description, Task.parseDate(timing));
        } else {
            boolean isDone = Boolean.parseBoolean(input.split("/completed ")[1]);
            input = input.split("/completed ")[0];
            String description = input.split("/at ")[0];
            String timing = input.split("/at ")[1];
            return new Event(description, Task.parseDate(timing), isDone);
        }
    }

    @Override
    public String getFileString() {
        return "event " + this.description + " /at " + Task.formatDate(this.timing) + " /completed " + this.isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Task.formatDate(this.timing) + ")";
    }
}

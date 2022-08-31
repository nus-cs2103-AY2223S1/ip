package duke.task;

import duke.Date;
import duke.Task;

/**
 * Creates a Task object that occurs at a specific time.
 * @author Jason
 */
public class Event extends Task {

    protected Date at;

    /**
     * Constructs an Event object.
     * @param description Description of the Event object.
     * @param at Date which the Event object is on.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Writes this Event task into the save file format.
     * @return String to be stored in save file.
     */
    @Override
    public String saveData() {
        String marked = this.isDone ? "1" : "0";
        return "E | " + marked + " | " + this.description + " | " + this.at;
    }
}

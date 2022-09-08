package duke.task;

import duke.Date;

/**
 * Creates a Task object that occurs at a specific time.
 * @author Jason
 */
public class Event extends Task {

    /**
     * Constructs an Event object.
     * @param description Description of the Event object.
     * @param at Date which the Event object is on.
     */
    public Event(String description, Date at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }

    /**
     * Writes this Event task into the save file format.
     * @return String to be stored in save file.
     */
    @Override
    public String saveData() {
        String marked = this.isDone ? "1" : "0";
        return "E | " + marked + " | " + this.description + " | " + this.date;
    }
}

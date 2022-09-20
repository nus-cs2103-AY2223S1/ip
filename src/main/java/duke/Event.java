package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates all tasks which occur on a specified date.
 */
class Event extends Task {

    LocalDate eventTime;

    /**
     * Creates a new Event task.
     * @param name The task to be completed.
     * @param eventTime The date at which the event will occur.
     */
    Event (String name, String eventTime) {
        super(name);
        this.eventTime = LocalDate.parse(eventTime);
    }

    /**
     * Creates a new Event task.
     * @param name The task to be completed.
     * @param eventTime The date at which the event will occur.
     * @param done The status of the task.
     */
    Event (String name, String eventTime, boolean done) {
        super(name, done);
        this.eventTime = LocalDate.parse(eventTime.trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
        assert(this.eventTime.isAfter(LocalDate.now()));
    }

    @Override
    public String toString() {
        String out = "[E][";
        if (super.getStatus()) {
            out += "X";
        } else {
            out += " ";
        }
        out += "] " + super.toString() + "(at : " + eventTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return out;
    }
}

package Duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Duke.DukeException;
import Duke.util.DukeDate;

/**
 * Duke.Duke.task.task.Event class inherits from Task
 * Duke.Duke.task.task.Event objects are tasks with specific start and end time
 * @author Nam Minh Quan
 */

public class Event extends Task {
    protected LocalDateTime time;

    public Event(String description, String time, boolean done) throws DukeException {
        super(description, done);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (time.equals("")) {
            throw new DukeException("☹ OOPS!!! The time of an event cannot be empty.");
        }
        this.time = new DukeDate(time).getDateTime();
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Sets a new start and end time
     * @param newTime new time for Duke.Duke.task.task.Event
     */
    public void setTime(String newTime) {
        this.time = new DukeDate(newTime).getDateTime();
    }

    @Override
    public boolean isTodo() {
        return false;
    };
    @Override
    public boolean isDeadline() {
        return false;
    };
    @Override
    public boolean isEvent() {
        return true;
    };

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[E]" + super.toString() + " (at:" + this.time.format(formatter) + ")";
    }
}

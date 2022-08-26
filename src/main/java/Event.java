import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class inherits from Task
 * Event objects are tasks with specific start and end time
 * @author Nam Minh Quan
 */

public class Event extends Task {
    protected LocalDateTime time;

    public Event(String description, String time) throws DukeException{
        super(description);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (time.equals("")) {
            throw new DukeException("☹ OOPS!!! The time of an event cannot be empty.");
        }
        this.time = new DukeDate(time).getDateTime();
    }

    /**
     * Sets a new start and end time
     * @param newTime new time for Event
     */
    public void setTime(String newTime) {
        this.time = new DukeDate(newTime).getDateTime();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at:" + this.time.format(formatter) + ")";
    }
}

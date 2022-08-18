/**
 * Event class inherits from Task
 * Event objects are tasks with specific start and end time
 * @author Nam Minh Quan
 */

public class Event extends Task {
    protected String time;

    public Event(String description, String time) throws DukeException{
        super(description);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (time.equals("")) {
            throw new DukeException("☹ OOPS!!! The time of an event cannot be empty.");
        }
        this.time = time;
    }

    /**
     * Sets a new start and end time
     * @param time new time for Event
     */
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.time + ")";
    }
}

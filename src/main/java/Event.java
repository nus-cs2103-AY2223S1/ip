/**
 * Event class inherits from Task
 * Event objects are tasks with specific start and end time
 * @author Nam Minh Quan
 */

public class Event extends Task {
    protected String time;

    public Event(String description, String time, boolean done) throws DukeException{
        super(description, done);
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (time.equals("")) {
            throw new DukeException("☹ OOPS!!! The time of an event cannot be empty.");
        }
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "[E]" + super.toString() + " (at:" + this.time + ")";
    }
}

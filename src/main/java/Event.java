/**
 * The Deadline class extends the Task class as it is a more specific type of task.
 */
public class Event extends Task {

    private String dateTime;

    /**
     * Public constructor for an Event.
     *
     * @param name name/description of the task.
     * @param dateTime when the Event is occurring.
     */
    public Event(String name, String dateTime) {
        super(name.substring(6));
        this.dateTime = dateTime;
    }

    /**
     * Overrides the toString() method in the Task class, represents an Event by adding a "[E]" in front of the
     * general Task representation.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.dateTime + ")";
    }
}

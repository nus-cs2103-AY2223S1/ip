package duke.task;

/**
 * Event Task.
 */
public class Event extends TimedTask {

    /**
     * @param description Description of the Event task.
     * @param by YYYY-MM-DD format of the Event.
     */
    public Event(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.by + ")";
    }


    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s", getStatusIcon(), super.description, super.by);
    }

}

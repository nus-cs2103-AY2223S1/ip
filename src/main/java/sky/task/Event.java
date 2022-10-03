package sky.task;

/**
 * The Event class encapsulates an Event.
 */
public class Event extends Task {
    protected String duration;

    /**
     * Constructs an Event with provided description and duration during.
     *
     * @param description Description of the task to be done.
     * @param duration Date and time, in the format of yyyy/mm/dd XXXX-XXXX, where XXXX is time in 24-hours.
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public Event makeACopy() {
        Event copiedTask = new Event(super.description, this.duration);
        return copiedTask;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.duration + ")";
    }
}

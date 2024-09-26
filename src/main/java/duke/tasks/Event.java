package duke.tasks;

public class Event extends Task {

    protected String date;

    /**
     * Create Event object.
     * @param description the task description.
     * @param at the timing information.
     */
    public Event(String description, String at) {
        super(description);
        this.date = at;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}

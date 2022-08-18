public class Event extends Task{
    /**
     * Event object at field which indicates the timings
     */
    protected String at;

    /**
     * A constructor to intialize the Event object with the description and timings
     *
     * @param description The task
     * @param at The timings
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + super.toString() + " (at: " + at + ")";
    }
}

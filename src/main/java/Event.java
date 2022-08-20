public class Event extends Task {
    /**
     * The time of Event
     */
    protected String time;

    /**
     * Constructor for Event
     * @param title The title of Event
     * @param time The time of Event
     */
    public Event(String title, String time) {
        super(title);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}

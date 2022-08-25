package arc;

public class Event extends Task {
    /**
     * The time of aRC.Event
     */
    private String time;

    /**
     * Constructor for aRC.Event
     * @param title The title of aRC.Event
     * @param isDone The isDone status of the aRC.Task
     * @param time The time of aRC.Event
     */
    public Event(String title, boolean isDone, String time) {
        super(title, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }

    @Override
    public String fileFormat() {
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.title, this.time);
    }
}

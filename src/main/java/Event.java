public class Event extends Task {
    protected String time;
    /**
     * Construct Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    /**
     * Shows the event name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[T]%s (by: %s)", super.toString(), time);
    }
}

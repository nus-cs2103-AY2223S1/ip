public class Event extends Task {
    private String timing;

    public static final String ENCODED_TASK_TYPE = "E";

    public Event(String description, String deadline) {
        super(description);
        this.timing = deadline;
    }

    public String getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return String.format("[E] %s %s (at: %s)", this.getStatusIcon(), this.getDescription(), this.getTiming());
    }

    /**
     * @return The task-representation of an event written to the file
     */
    @Override
    public String encode() {
        return String.format("%s|%d|%s|%s\n", ENCODED_TASK_TYPE, getIsDone() ? 1 : 0, this.getDescription(), this.getTiming());
    }
}

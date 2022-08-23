public class Event extends Task {
    private static final String TYPE_SYMBOL = "[E]";
    private String date;

    public Event(String task, String date) {
        super(task);
        this.date = date;
    }

    public Event(String task, String date, boolean isDone) {
        super(task, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString() + " (at: " + date + ")";
    }

    @Override
    public String toSaveFileString() {
        return TYPE_SYMBOL + " @ " + getStatusIcon() + " @ " + super.getTask() + " @ " + date;
    }
}

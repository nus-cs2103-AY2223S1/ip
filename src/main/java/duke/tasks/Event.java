package duke.tasks;

public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "[E]" + s + " (at: " + time +")";
    }

    public String toFile() {
        String s = super.toFile();
        return "E," + s + "," + time;
    }
}

package duke;

public class Event extends Task {
    private String time;

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", super.getStatusIcon(),
                super.toString(), this.time);
    }

    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s", super.getFileIcon(), super.toString(), this.time);
    }
}

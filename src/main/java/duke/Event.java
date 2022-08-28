package duke;

public class Event extends Task {
    String date;
    boolean isDone;

    public Event(String description, boolean isDone, String date) {
        super(description.trim());
        this.isDone = isDone;
        this.date = date.trim();
        Task.taskCount++;
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s | %s", this.getStatusIcon(), this.description, this.date);
    }

}

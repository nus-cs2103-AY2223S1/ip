package duke.task;

public class Event extends Task {
    protected String event;

    public Event(String taskName, String event) {
        super(taskName);
        this.event = event;
    }

    @Override
    public String inputToTxt() {
        return String.format("E | %s | %s | %s\n",
                (this.isDone ? "1" : "0"),
                this.taskName, this.event);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + event + ")";
    }
}

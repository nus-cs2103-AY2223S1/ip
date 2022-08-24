package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, String status) {
        this(description, at);
        if (status.equals("1")) {
            super.markAsDone();
        }
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public String getDate() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

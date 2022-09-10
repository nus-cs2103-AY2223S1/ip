package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String convertToFileFormat() {
        return String.format("event | %s | %s | %b", super.description, at, super.isDone);
    }

    /**
     * Returns string representation of this task.
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}

package duke.models;

public class Event extends Task {

    protected String at;
    protected FormattedDate formattedDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.formattedDate = new FormattedDate(at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        this.formattedDate = new FormattedDate(at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.formattedDate);
    }
}
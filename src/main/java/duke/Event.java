package duke;

public class Event extends Task {

    private final String at;

    public Event(String description) {
        super(description.split("/at")[0]);
        this.at = description.split("/at")[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", super.getStatusIcon(), description, at);
    }
}

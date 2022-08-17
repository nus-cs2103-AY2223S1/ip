package duke.tasks;

public class Event extends Task {

    private final String at;

    public Event(String description) {
        super(description.split("/at")[0].trim());
        this.at = description.split("/at")[1].trim();
    }

    @Override
    public String savedString() {
        String status = isDone ? "1" : "0";
        return String.format("E | %s | %s | %s", status, description, at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", super.getStatusIcon(), description, at);
    }
}

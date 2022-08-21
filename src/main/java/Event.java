public class Event extends Task {
    String details;

    Event(String description, boolean isDone, String details) {
        super(description, isDone);
        this.details = details;
    }

    public String SaveString() {
        return String.format("E | %s | %s | %s\n", super.isDone ? "1" : "0",
                super.description, details);
    }
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.details);
    }
}

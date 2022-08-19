/**
 * A task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    private final String datetime;

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    public Event(String description, String datetime, boolean isDone) {
        super(description, isDone);
        this.datetime = datetime;
    }

    @Override
    public String serialize() {
        return String.format("E|%s|%s|%s", isDone, description, datetime);
    }

    public static Task deserialize(String str) {
        String[] parts = str.split("\\|");
        if (parts.length != 4 || !parts[0].equals("E")) {
            throw new IllegalArgumentException("Invalid event format");
        }
        return new Event(parts[2], parts[3], Boolean.parseBoolean(parts[1]));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}

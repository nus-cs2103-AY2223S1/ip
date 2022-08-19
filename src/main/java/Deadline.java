/**
 * A task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String serialize() {
        return String.format("D|%s|%s|%s", isDone, description, deadline);
    }

    public static Task deserialize(String str) {
        String[] parts = str.split("\\|");
        if (parts.length != 4 || !parts[0].equals("D")) {
            throw new IllegalArgumentException("Invalid deadline format");
        }
        return new Deadline(parts[2], parts[3], Boolean.parseBoolean(parts[1]));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}

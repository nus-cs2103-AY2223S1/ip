public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toFileRepresentation() {
        return String.format("E | %s | %s", super.toFileRepresentation(), this.at);
    }

    public static Event fromFileRepresentation(String rep) {
        String[] args = rep.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String description = args[2];
        String date = args[3];
        Event result = new Event(description, date);
        if (isDone) {
            result.markDone();
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.at);
    }
}

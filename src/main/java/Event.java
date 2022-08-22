public class Event extends Task {
    private static final String type = "[E]";
    private String time;

    public Event(String name, int count, String time) throws MissingDescriptionException {
        super(name, count);
        this.time = "(at: " + time + ")";
    }

    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return String.format("%d." + type + comp + name + time, count);
    }

    @Override
    public String toStr() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return type + comp + name + time;
    }
}

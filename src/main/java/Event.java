public class Event extends Task {
    private final String time;
    private static final String type = "[E]";

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + super.toString() + " (on: " + time + ")";
    }
}

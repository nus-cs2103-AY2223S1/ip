public class Event extends Task {
    private String at;

    public Event(String item, String at) {
        super(item);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}

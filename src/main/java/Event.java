public class Event extends Task {
    private String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.at + ")";
    }
}

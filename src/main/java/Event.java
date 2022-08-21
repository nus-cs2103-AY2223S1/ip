public class Event extends Task {
    private String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    public Event(String desc, String at, boolean isDone) {
        super(desc, isDone);
        this.at = at;
    }

    @Override
    public String toSaveFormat() {
        return "E " + super.toSaveFormat() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.at + ")";
    }
}

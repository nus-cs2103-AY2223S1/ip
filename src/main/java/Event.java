public class Event extends Task {
    protected String at;

    public Event(String desc, String at) throws EmptyDescException {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toSave() {
        return "E | " + (isDone ? "1" : "0") + " |" + desc + " | " + at;
    }
}

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toSave() { return "E | " + super.toSave() + "| " + at; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "AT: " + at + "!";
    }
}

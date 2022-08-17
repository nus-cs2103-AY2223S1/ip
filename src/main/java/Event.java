public class Event extends Task{

    protected String at;

    public Event(String detail, String at) {
        super(detail);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

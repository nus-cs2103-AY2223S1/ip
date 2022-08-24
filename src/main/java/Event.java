public class Event extends Task{

    protected String at;

    public Event(String description, String date) {
        super(description);
        this.at = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

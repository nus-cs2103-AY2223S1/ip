public class Event extends Task {

    protected String due;

    public Event (String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + due + ")";
    }
}

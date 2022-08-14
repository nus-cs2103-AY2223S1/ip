/**
 * Event class has a date field.
 */
public class Event extends Task{
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = String.format("[E][✓] %s", this.description);
        } else {
            status = String.format("[E][ ] %s (on: %d)", this.description, date);
        }
        return status;
    }
}

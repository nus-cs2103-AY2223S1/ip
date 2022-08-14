/**
 * This class encapsulates an event set by the user.
 */
public class Event extends Task {
    private String date;

    Event(String content, String date) {
        super(content);
        this.date = date;
    }

    /**
     * Returns the String representation of this event.
     *
     * @return A String representing this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}


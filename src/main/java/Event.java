// Event a child class of Task has the same functionality
// but adds on with an at field which allows users to set an event timing.

public class Event extends Task {

    protected String at;

    // Constructor
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    // toString method to change the display for different types of tasks on the console
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

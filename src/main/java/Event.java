// This class inherits from the abstract Task class
// and encapsulates the logic of an Event task.
public class Event extends Task {
    /* Duration field */
    private String duration;

    /**
     * Constructor for the Event Task.
     * @param description description of the task.
     */
    public Event (String description) {
        super(description);
        String[] temp = description.split("/at ");
        this.description = temp[0];
        duration = temp.length < 2 ? "No duration given" : temp[1];
    }

    /**
     * Override toString method for the Event Task.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +" (at: " + duration + ")";
    }

    /**
     * Override save format method from Task class.
     *
     * @return formatted String for the Event task.
     */
    @Override
    public String saveFormat() {
        return String.format("E | %s | %s", super.saveFormat(), this.duration);
    }
}

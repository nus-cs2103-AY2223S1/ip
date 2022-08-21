/**
 * Event class that inherits from Task.
 */
public class Event extends Task {

    protected String datetime;

    /**
     * Constructor of Event class.
     *
     * @param description a String that describes the Event
     * @param datetime    a String that represents the datetime of the Event
     */
    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Returns Event as a String formatted to be a line in file.
     *
     * @return String representing event.
     */
    @Override
    public String getFileFormat() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.datetime);
    }

    /**
     * Returns a String that represents the Event.
     *
     * @return a String that represents the Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.datetime + ")";
    }
}

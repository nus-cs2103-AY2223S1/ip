package ted.task;

/**
 * A class that encapsulate a event task
 */
public class Event extends Task {

    /**
     * Event's date
     */
    private String at;

    /**
     * Construct an Event instance
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Encode to a string that can be stored in file
     * @return string that is store-able
     */
    @Override
    public String encode() {
        return String.format("E | %s | %s", super.encode(), this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

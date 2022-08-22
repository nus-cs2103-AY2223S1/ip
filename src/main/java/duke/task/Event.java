package duke.task;

/**
 * Encapsulates a Event
 */
public class Event extends Task {
    private String at;

    /**
     * Constructor for an Event
     *
     * @param name Name / Description of the event
     * @param at Description of where and when the event is at
     */
    public Event(String name, String at) {
        super(name, 'E');
        this.at = at;
    }

    /**
     * Returns string representation of the event consisting of the string representation of Event, [E], the completion
     * status of the event and the event description
     * */
    @Override
    public String toString() {
        return String.format(super.toString() + " (at: %s)", at);
    }
}

public class Event extends Task {
    private String period;

    public Event(String name, String period) {
        /**
         * Constructor for Event class. Sets the event name and time period.
         *
         * @param name The task name.
         * @param period The time period where the event will take place.
         */
        super(name);
        this.period = period;
    }

    public String toString() {
        /**
         * String representation of an event. Also indicates if the event is done.
         */
        return "[E]" + super.toString() + " (at: " + this.period + ")";
    }
}

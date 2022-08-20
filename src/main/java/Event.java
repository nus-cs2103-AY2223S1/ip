package main.java;

public class Event extends Task {

    /**
     * Deadline of 'Deadline' object
     */
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Override 'toString' method to return status and description of
     * 'Event' object.
     * @return [E][COMPLETION STATUS][TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * To produce a String with "|" delimiters for storing the task's data
     * into a text file.
     * @return "event|[COMPLETION STATUS]|[TASK DESCRIPTION]|[TASK VENUE]"
     */
    public String toFileFormat() {
        return "event" + "|"
                    + this.isDone + "|"
                    + this.description + "|"
                    + this.at;
    }
}

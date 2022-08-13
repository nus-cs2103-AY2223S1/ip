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
     * 'Deadline' object.
     * @return [E][COMPLETION STATUS][TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

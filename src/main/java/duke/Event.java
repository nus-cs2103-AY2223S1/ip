package duke;

/**
 * A class that creates an object of type Event
 * which are tasks that have a timing with keyword "at".
 *
 * @author Safwan A0235287X
 */
public class Event extends Task{

    protected String at;

    /**
     * Constructor to create Event object.
     * @param description Content of the task.
     * @param at Timing of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Method that overrides Java.toString() method to convert the Deadline
     * task as a string.
     * @return A string of the Deadline task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}


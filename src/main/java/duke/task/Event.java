package duke.task;


/**
 * Represents an event task
 *
 * @author benjytan45678
 * @version 0.1
 */
public class Event extends Task {
    private String time;
    private String name;

    /**
     * Instantiates a event task with specified name and time.
     *
     * @param name name of the event task
     * @param time time of the event task
     */
    public Event(String name,String time) {
        super(name);
        this.time = time;
        this.name = name;
    }

    /**
     * Creates a String representation of the event task
     *
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }

    /**
     * Creates a simplified version of the event task to be stored in local file
     *
     */
    public String parse() {
        if (this.getHasCompleted()) {
            return "E#1#" + this.name + "#" + this.time;
        } else {
            return "E#0#" + this.name + "#" + this.time;
        }

    }
}

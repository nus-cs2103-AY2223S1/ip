package duke;

public class Event extends Task {
    /** The time of the event. **/
    protected String at;

    /**
     * The class constructor, which utilizes the superclass
     * constructor.
     * @param description the description of the task
     * @param at the time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats and outputs the string representation of an duke.Event viable for writing
     * into text file.
     * @return formatted txt file string representation of an duke.Event.
     */
    @Override
    public String formatFileText() {
        String s = String.format("E | %s | %s | %s\n", super.getStatusIcon(), this.description, this.at);
        return s;
    }

    /**
     * Overrides the toString method of the superclass to add
     * the additional [E] tag.
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}

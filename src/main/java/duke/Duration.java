package duke;


/**
 * Has description and isDone field to describe and mark completion status of a task.
 * Also has a duration field to indicate the time it takes to complete the task.
 *
 * @author Yuvaraj Kumaresan
 */
public class Duration extends Task {
    /**
     * Describes the task.
     */
    protected String description;

    /**
     * States if the task is done or not.
     */
    protected boolean isDone;

    /**
     * String containing the duration of the task.
     */
    protected String duration;

    /**
     * Constructor.
     *
     * @param description The string describing the task.
     */
    public Duration(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Converts event object to its string representation.
     *
     * @return String representation of the event object.
     */
    @Override
    public String toString() {
        return "[DT]" + super.toString() + " (needs " + duration + ")";
    }

    /**
     * Gets the duration needed to finish the task.
     *
     * @return The duration of the task.
     */
    public String getTime() {
        return this.duration;
    }
}

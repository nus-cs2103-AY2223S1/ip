package duke.task;

/**
 * Defines {@code Event} class.
 */
public class Event extends Task {
    /** Venue of event. */
    protected String at;

    /**
     * Constructor for {@code Event}.
     * @param description Description of task.
     * @param at          Deadline of task.
     * @param priority    Priority level of task.
     */
    public Event(String description, String at, Level priority) {
        super(description, priority);
        this.at = at;
    }

    /**
     * Constructor for {@code Event} with {@code isDone} known.
     * @param description Description of task.
     * @param at          Deadline of task.
     * @param isDone      Whether task is done.
     * @param priority    Priority level of task.
     */
    public Event(String description, String at, Boolean isDone, Level priority) {
        super(description, isDone, priority);
        this.at = at;
    }

    /**
     * Overrides {@code toString} method to return status and
     * description of {@code Event} object.
     * @return [E][[COMPLETION STATUS]][[PRIORITY]] [TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Produces a {@code String} with "|" delimiters for
     * storing the {@code Event}'s data into a text file.
     * @return "event|[COMPLETION STATUS]|[TASK DESCRIPTION]
     *               |[PRIORITY]|[TASK VENUE]"
     */
    public String toFileFormat() {
        return "event" + "|" + super.toFileFormat() + "|" + this.at;
    }

    /**
     * Returns whether {@code Event}'s description or venue contains
     * given word.
     * @param word Word to search for.
     * @return     {@code Boolean} value of whether {@code Event}'s
     *             description or venue contains given word.
     */
    @Override
    public Boolean hasWord(String word) {
        return super.hasWord(word) || this.at.contains(word);
    }
}

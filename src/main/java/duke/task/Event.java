package duke.task;

/**
 * Defines <Code>Event</Code> class.
 */
public class Event extends Task {
    /** Venue of event. */
    protected String at;

    /**
     * Constructor for <Code>Event</COde>.
     * @param description Description of task.
     * @param at          Deadline of task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for <Code>Event</Code> with <Code>isDone</Code> known.
     * @param description Description of task.
     * @param at          Deadline of task.
     * @param isDone      Whether task is done.
     */
    public Event(String description, String at, Boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Overrides <Code>toString</Code> method to return status and
     * description of <Code>Event</Code> object.
     * @return [E][COMPLETION STATUS][TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Produces a <Code>String</Code> with "|" delimiters for
     * storing the <Code>Event</Code>'s data into a text file.
     * @return "event|[COMPLETION STATUS]|[TASK DESCRIPTION]|[TASK VENUE]"
     */
    public String toFileFormat() {
        return "event" + "|" + super.toFileFormat() + "|" + this.at;
    }

    /**
     * Returns whether <Code>Event</Code>'s description or venue contains
     * given word.
     * @param word Word to search for.
     * @return     <Code>Boolean</Code> value of whether <Code>Event</Code>'s
     *             description or venue contains given word.
     */
    @Override
    public Boolean hasWord(String word) {
        return super.hasWord(word) || this.at.contains(word);
    }
}

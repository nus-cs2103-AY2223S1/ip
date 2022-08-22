package duke.task;

public class Event extends Task {

    /**
     * Deadline of 'Deadline' object
     */
    protected String at;

    /**
     * Constructor method for `Event`.
     * @param description Description of task.
     * @param at          Deadline of task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor method for `Event`.
     * @param description Description of task.
     * @param at          Deadline of task.
     * @param isDone      Whether task is done.
     */
    public Event(String description, String at, Boolean isDone) {
        super(description, isDone);
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
                    + super.toFileFormat() + "|"
                    + this.at;
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

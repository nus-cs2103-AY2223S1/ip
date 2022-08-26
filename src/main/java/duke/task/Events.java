package duke.task;

/**
 * Represents an Events task. A Event object contains the description and when event is happening
 */
public class Events extends Task {
    protected String by;

    /**
     * Constructor for Event
     * @param description String that describes the task
     * @param by String that denotes when event is happening
     */
    public Events(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     *
     * @return String to save onto text document
     */
    @Override
    public String textFormat() {
        return "E|" + (isDone ? 1 : 0) + "|" + description + "|" + by;
    }

    /**
     *
     * @return String to be displayed to users
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }

}

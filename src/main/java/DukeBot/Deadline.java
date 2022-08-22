package DukeBot;
public class Deadline extends Task {

    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the type of Task.
     *
     * @return "D" indicating Deadline.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Get the time of the Deadline.
     *
     * @return by The time of the Deadline.
     */
    @Override
    public String getTime() {
        return by;
    }

    /**
     * Get string representation of Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: " + this.by + ")", this.getTaskType(), super.toString());
    }
}

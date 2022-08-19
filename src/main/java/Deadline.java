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
    public String getTaskType() {
        return "D";
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

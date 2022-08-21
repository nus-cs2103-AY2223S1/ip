public class Deadline extends Task {
    /** The deadline of the task. **/
    protected String by;

    /**
     * The class constructor, which utilizes the superclass
     * constructor.
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String formatFileText() {
        String s = String.format("D | %s | %s | %s\n", super.getStatusIcon(), this.description, this.by);
        return s;
    }

    /**
     * Overrides the toString method of the superclass to add
     * the additional [D] tag.
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }

}

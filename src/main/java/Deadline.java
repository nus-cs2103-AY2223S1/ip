public class Deadline extends Task {
    /* Time frame that the Deadline object should be completed by*/
    protected String by;

    /**
     * Constructor for Deadline Class.
     * @param name String representation of task name.
     * @param by String representation of task deadline.
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns string representation of Deadline object.
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

public class Deadline extends Task{

    protected String by;

    /**
     * Constructor for task.
     *
     * @param detail String
     */
    public Deadline(String detail, String by) {
        super(detail);
        this.by = by;
    }
    public Deadline(String detail, boolean isDone, String by) {
        super(detail, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()  + " (by: " + by + ")";
    }

    @Override
    public String storedData() {
        return "D" + "|" + super.storedData() + "|" + by;
    }
}

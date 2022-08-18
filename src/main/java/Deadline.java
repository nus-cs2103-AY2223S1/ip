public class Deadline extends Task{
    protected String description;
    protected boolean isDone;
    protected String date;

    /**
     * Constructor for a Deadline object
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}

public class Deadline extends Task {
    private String datetime;

    /**
     * Constructor for Deadline instance
     * @param name Description of task
     * @param datetime Deadline of task
     */
    public Deadline(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Returns the deadline of the task.
     * @return Task deadline
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Sets the deadline of the task to the input deadline.
     * @param datetime New deadline
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.datetime + ")";
    }
}

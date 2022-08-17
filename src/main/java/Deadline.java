public class Deadline extends Task {
    private String dueDate;

    /**
     * Constructor to create a new Deadline
     *
     * @param task  the task that you want to complete (String)
     * @param dueDate  the dueDate for this deadline (String)
     */
    public Deadline(String task, String dueDate) {
        this.task = task;
        this.dueDate = dueDate;
    }

    /**
     * To String method that returns the task in string form to the user
     *
     * @return  the task in string format
     */
    @Override
    public String toString() {
        return "[D] " + this + " (by: " + this.dueDate + ")";
    }
}

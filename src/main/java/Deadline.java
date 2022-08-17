/**
 * This class Deadline tasks
 */
public class Deadline extends Task{
    private final String ddlTime;

    /**
     * Constructor for Deadline class
     * @param taskDescription the content of the task
     * @param ddlTime the deadline time
     */
    public Deadline(String taskDescription, String ddlTime) {
        super(taskDescription);
        this.ddlTime = ddlTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + this.ddlTime + ")";
    }

}

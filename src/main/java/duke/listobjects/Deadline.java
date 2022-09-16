package duke.listobjects;

/**
 * Represents Deadlines which are ListObjects that store deadline (date and time) with the task
 */
public class Deadline extends ListObject{

    /**
     * Constructs a Deadline object with given task description, status, and deadline
     * @param task String description of tas
     * @param status int representing completion status as 1 if finished and 0 otherwise
     * @param doBy String representing deadline (date and time) for task
     */
    public Deadline (String task, int status, String doBy) {
        super(task, doBy, status);
    }

    /**
     * Returns the String representation of the Deadline object
     * @return String representation of Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.formatDateTime("d") + ")";
    }


}

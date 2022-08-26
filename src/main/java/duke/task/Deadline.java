package duke.task;

/**
 * The Deadline class extends from Task as it is a more specific/well-defined
 * task, it is differentiated with the "deadline" keyword in user-input.
 */
public class Deadline extends Task {

    //String field to store details after /by keyword
    protected String by;

    /**
     * Constructor method for an instance of Deadline and it also utilises
     * the constructor of its parent class (Task)
     *
     * @param description String that contains the details about the task
     * @param by String that contains the information of the deadline
     * @param isDone a boolean to keep track if the task has been marked before
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * A string method to represent the Deadline task.
     *
     * @return a string representation of a task with a Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

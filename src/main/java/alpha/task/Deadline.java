package alpha.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    /** Deadline for the task */
    protected String deadline;

    /**
     * Constructor to initialise the global variables.
     *
     * @param description To initialise the task description.
     * @param deadline To initialise the deadline for the task.
     * @param taskType To initialise the task type.
     */
    public Deadline(String description, String deadline, String taskType) {
        super(description, taskType);
        this.deadline = deadline;
    }

    /**
     * Returns the task deadline.
     *
     * @return Task deadline.
     */
    public String getDeadline(){
        return this.deadline;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the deadline in red font colour along with the other details of the task.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(uI.ANSI_RED + " (by: %s)", this.getDeadline() + uI.ANSI_RESET);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects.
     * Returns true if both objects are instance of Deadline class and have the same attributes.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            return (d.getDescription().equals(this.getDescription()) && d.getStatus().equals(this.getStatus()) && d.getTaskType().equals(this.getTaskType()) && d.getDeadline().equals(this.getDeadline()));
        }
        return false;
    }
}

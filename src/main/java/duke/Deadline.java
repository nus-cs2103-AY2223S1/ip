package duke;

import java.time.LocalDate;

/**
 * Represents a deadline task, or a task to be done by the user by the deadline.
 * It is a type of task, and hence inherits from <code>Task</code> class.
 */
public class Deadline extends Task {

    protected LocalDate deadline;

    /**
     * Constructor for Deadline Task.
     *
     * @param taskName the description of the task to be done.
     * @param isDone   completion status of the task.
     * @param deadline date by which task must be done.
     */
    public Deadline(String taskName, boolean isDone, String deadline, int priority) {
        super(taskName, isDone, priority);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "]" + "[" +
                this.getPriority() + "] " + this.taskName + " (by: " +
                this.deadline.getDayOfWeek() + ", " +
                this.deadline.getMonth() + " " + this.deadline.getDayOfMonth() +
                " " + this.deadline.getYear() + " " + ")";
    }

    /**
     * Returns the string representation of the task which is saved locally, for easy retrieval.
     *
     * @return string representation of deadline task which is saved.
     */
    @Override
    public String toStore() {
        return "|D|" + "|" + this.getStatusIcon() + "|" + "|" + this.priority + "| "
                + this.taskName + " (by: " + this.deadline.toString() + ")";
    }
}

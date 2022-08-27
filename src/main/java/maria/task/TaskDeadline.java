package maria.task;

import java.time.LocalDate;

import maria.util.DukeDateTimeFormatter;

/**
 * Represents a Task of type Deadline.
 */
public class TaskDeadline extends Task {

    private LocalDate deadline;

    /**
     * Creates a Deadline Task.
     * @param name The name of the task
     * @param isDone Whether the task is completed
     * @param deadline The time in which the task must be completed by
     * @throws TaskNoNameException If the name is empty
     */
    public TaskDeadline(String name, boolean isDone, LocalDate deadline) throws TaskNoNameException {
        super(name, isDone);
        this.deadline = deadline;
    }

    /**
     * Gets the string representation of the task.
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[Deadline]" + super.toString() + " (by: " + DukeDateTimeFormatter.formatDisplay(this.deadline) + ")";
    }

    /**
     * Gets the storage string representation of the task.
     * @return The storage string representation of the task
     */
    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "deadline" + "|||"
                + DukeDateTimeFormatter.formatStorage(this.deadline);
    }

}

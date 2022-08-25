package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task with a given deadline.
 *
 * @author Conrad
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for creating a task with a deadline.
     *
     * @param taskDescription A description of the task.
     * @param taskDeadline The time by which the task has to be completed.
     */
    public Deadline(String taskDescription, LocalDate taskDeadline) {
        super(taskDescription);
        this.deadline = taskDeadline;
    }

    @Override
    public String getTextRepresentation() {

        return "D|" + (this.isCompleted() ? "1|" : "0|") + this.getTaskDescription() + "|" + this.deadline + "\n";
    }

    /**
     * Return a string representation of a task with a deadline.
     *
     * @return The string representation of a task with a deadline.
     */
    @Override
    public String toString() {
        String taskStatusIndicator = "[D]" + (this.isCompleted() ? "[X] " : "[ ] ");
        return taskStatusIndicator + this.getTaskDescription()
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

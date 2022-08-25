package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    protected LocalDate dateline;

    /** Constructor for deadline duke.task.
     *
     * @param description duke.task description
     * @param dateline by when this task has to be completed
     * @return a new DeadlineTask
     */
    public DeadlineTask(String description, String dateline) {
        super(description);
        this.dateline = LocalDate.parse(dateline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + this.dateline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String encodeToString() {
        String taskStatus = this.isDone ? "Done" : "Undone";
        return String.format("D | %s | %s | %s", taskStatus, this.description, this.dateline);
    }
}

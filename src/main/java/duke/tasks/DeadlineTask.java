package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific date/time
 */
public class DeadlineTask extends Task {

    private final LocalDate by;

    /**
     * Constructor for DeadlineTask
     *
     * @param description Description of task details
     * @param by          When user requires the task to be done by
     */
    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for EventTask and isDone initialised
     *
     * @param description Description of task details
     * @param by          When user requires the task to be done by
     * @param isDone      Whether task has been done
     */
    public DeadlineTask(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns String representation of Deadline
     *
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

    @Override
    public String toSaveString() {
        return "D " + super.toSaveString() + " | " + this.by;
    }
}

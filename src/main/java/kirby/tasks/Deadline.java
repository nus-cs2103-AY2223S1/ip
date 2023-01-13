package kirby.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import kirby.time.HandleTime;

/**
 * Deadline class contains information of a Deadline task.
 */
public class Deadline extends Task {
    protected String by;
    private LocalDate localDate = null;

    /**
     * Constructor for the class Deadline.
     *
     * @param description Description of the task.
     * @param by Argument of the task which contains the deadline date.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        if (HandleTime.isValidDate(by)) {
            this.localDate = LocalDate.parse(by);
        }
        if (isDone) {
            this.setCompleted();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getDate() {
        return HandleTime.fromStringToDate(by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (localDate != null) {
            return new StringBuilder().append("[D] ").append(this.getStatusIcon()).append(" ")
                    .append(this.description).append(" (by: ")
                    .append(localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy)"))).toString();
        } else {
            return "[D] " + this.getStatusIcon() + " " + this.description + " (by: " + by + ")";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileOutput() {
        return "kirby.tasks.Deadline~" + this.description + "~" + this.by + "~" + this.getStatusIcon();
    }
}

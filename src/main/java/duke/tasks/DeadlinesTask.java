package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task that represents Deadlines.
 */
public class DeadlinesTask extends Task {
    private LocalDateTime deadline;

    /**
     * Default constructor of the Deadlines Task.
     *
     * @param name Name of the deadlines task.
     * @param deadline Date of the deadlines Task.
     */
    public DeadlinesTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * {@inheritDoc}
     * @return String representation of Deadlines Task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "[" + TaskType.D + "]" + "[" + this.getStatusIcon() + "] " + this.getName()
                + " (by: " + this.getDeadline().format(formatter) + ")";
    }
    /**
     * Return the String representation of the object in CSV.
     * The following attributes are saved.
     * Type of task - D,E,T.
     * Marked status - X," ".
     * Name.
     * Date of deadline in YYYY-MM-DD HHmm.
     *
     * @return String representation of Deadlines Task in CSV.
     */
    @Override
    public String toCsv() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return TaskType.D + "," + this.getStatusIcon() + "," + this.getName() + ","
                + this.getDeadline().format(formatter);
    }
}

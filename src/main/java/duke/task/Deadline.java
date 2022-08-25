package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task with TaskType, description,
 * boolean to check whether the task is done, and time of deadline.
 */
public class Deadline extends Task {

    private LocalDateTime byTime;

    /**
     * Constructor for Deadline
     *
     * @param taskType TaskType of deadline
     * @param name Description of the deadline
     * @param isMarked Denotes whether the deadline is done yet
     * @param timeStr Time of deadline
     */
    public Deadline(TaskType taskType, String name, boolean isMarked, String timeStr) {
        super(taskType, name, isMarked);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "HHmm, d/MM/yyyy");
        this.byTime = LocalDateTime.parse(timeStr, formatter);
    }

    /**
     * @return Time of deadline
     */
    public LocalDateTime getByTime() {
        return this.byTime;
    }

    /**
     * {@inheritDoc}
     * @return String of deadline with details, TaskType, name, isMarked and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "hh:mm a, EEE, d MMM yyyy");
        return "[D]" + super.toString()
                + " (by: " + byTime.format(formatter) + ")";
    }
}


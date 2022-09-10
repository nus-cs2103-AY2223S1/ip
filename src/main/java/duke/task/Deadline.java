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
     * Constructor for Deadline.
     *
     * @param taskType TaskType of deadline
     * @param name Description of the deadline
     * @param isMarked Denotes whether the deadline is done yet
     * @param timeStr Time of deadline
     */
    public Deadline(TaskType taskType, String name, boolean isMarked, String timeStr) {
        super(taskType, name, isMarked);
        String format = "HHmm, d/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        this.byTime = LocalDateTime.parse(timeStr, formatter);
    }

    /**
     * @return Time of deadline
     */
    public LocalDateTime getByTime() {
        return this.byTime;
    }

    /**
     * @param newByTime new by time stated
     */
    public void setByTime(String newByTime) {
        String format = "HHmm, d/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        this.byTime = LocalDateTime.parse(newByTime, formatter);
    }

    /**
     * {@inheritDoc}
     * @return String of deadline with details, TaskType, name, isMarked and time.
     */
    @Override
    public String toString() {
        String format = "hh:mm a, EEE, d MMM yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return "[D]" + super.toString()
                + " (by: " + byTime.format(formatter) + ")";
    }
}


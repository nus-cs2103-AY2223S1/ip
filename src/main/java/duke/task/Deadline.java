package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private LocalDate day;
    private LocalTime time;

    /**
     * Constructs a task that has to be done before the specified date.
     *
     * @param description A brief description of the task.
     * @param day Date the task needs to be completed by.
     */
    public Deadline(String description, LocalDate day) {
        super(description);
        this.day = day;
    }

    /**
     * Constructs a task that has to be done before the specified date and time.
     *
     * @param description A brief description of the task.
     * @param day The date the task needs to be completed by.
     * @param time The time the task needs to be completed by.
     */
    public Deadline(String description, LocalDate day, LocalTime time) {
        super(description);
        this.day = day;
        this.time = time;
    }

    @Override
    public boolean isOn(LocalDate searchDate) {
        return this.day.equals(searchDate);
    }

    @Override
    public String fileDescription() {
        if (time == null) {
            return "D | " + super.fileDescription() + " | "
                    + day;
        } else {
            return "D | " + super.fileDescription() + " | "
                    + day + " | " + time;
        }
    }

    @Override
    public String toString() {
        if (time == null) {
            return "[D]" + super.toString() + " (by: " + day.format(super.dateFormatter) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + day.format(super.dateFormatter) + ", "
                    + time.format(super.timeFormatter) + ")";
        }
    }
}

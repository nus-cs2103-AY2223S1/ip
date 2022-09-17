package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline given by a date and time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor method for a Deadline.
     *
     * @param description description of the task
     * @param deadlineStr deadline given by a date and time
     */
    public Deadline(String description, String deadlineStr) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadlineStr, formatter);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineStr() + ")";
    }

    /**
     * Returns the deadline of the task.
     *
     * @return deadline of the task
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the string representation of the task deadline.
     *
     * @return string representation of the task deadline
     */
    public String getDeadlineStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE, hh:mma dd MMMM yyyy");
        return this.deadline.format(formatter);
    }

    /**
     * Returns the string representation of the Deadline to be saved.
     *
     * @return string representation of the Deadline to be saved
     */
    @Override
    public String save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String deadlineStr = this.deadline.format(formatter);
        return "D" + super.save() + " | " + deadlineStr;
    }

    /**
     * Tests whether the task deadline is on the given date.
     *
     * @param dateStr the given date
     * @return true if the deadline is on the given date, false otherwise
     */
    public boolean isOnDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate other = this.deadline.toLocalDate();
        return date.equals(other);
    }

    public boolean isAfter(LocalDateTime date) {
        return this.deadline.isAfter(date);
    }
}

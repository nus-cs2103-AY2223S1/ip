package jude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jude.Parser;
import jude.util.DateUtils;

/**
 * A {@code Deadline} object is a Task object that has an associated deadline by which the task
 * should be completed.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Creates a new {@code Deadline} object with a given description, whether it has been done
     * and the corresponding deadline.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is marked as done.
     * @param deadline The date/time by which the task should be completed.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the task the {@code Deadline} object is associated with.
     *
     * @return the deadline of the task the {@code Deadline} object is associated with.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns the task type code for an {@code Deadline} object, i.e. "D".
     *
     * @return "D".
     */
    @Override
    public String getTaskTypeCode() {
        return "D";
    }

    /**
     * Returns the String representation of the {@code Deadline} object, i.e.
     * a string in the format "[task type code][get status icon] description (by: deadline)".
     *
     * @return String representation of the {@code Deadline} object.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), deadline);
    }

    /**
     * Returns a String representation of the {@code Deadline} object which in a format convenient
     * to save and load files.
     *
     * The string returned is in the following format (with newlines in between components and in
     * the end):
     * Task Type Code, i.e. "D" for {@code Deadline} objects
     * Description
     * 1 if the task is done, and 0 otherwise
     * Deadline by which the task should be completed
     *
     * @return The String representation of the {@code Deadline} object.
     */
    @Override
    public String toFileSaveString() {
        return String.format("%s%s\n", super.toFileSaveString(), deadline);
    }

    /**
     * Returns whether the {@code Deadline} objects have a deadline which is coming soon (within
     * {@code seconds} of the current time) and is not marked as complete.
     * Also reminds of tasks which are overdue.
     *
     * @param seconds Number of seconds of reminder notice.
     * @return True if the {@code Deadline} is not marked as complete and has a deadline no greater
     *     than {@code seconds} seconds away or is overdue, and false otherwise.
     */
    @Override
    public boolean needsReminder(long seconds) {
        long timeToDeadline = DateUtils.calculateTimeDifference(LocalDateTime.now(),
                LocalDateTime.parse(this.deadline,
                DateTimeFormatter.ofPattern(Parser.DEFAULT_DATE_FORMAT)));
        boolean isDeadlineComingSoon = timeToDeadline <= seconds;
        return !getIsDone() && isDeadlineComingSoon;
    }
}

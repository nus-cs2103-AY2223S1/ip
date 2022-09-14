package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Nullable;

import duke.date.DateTimeParse;
import duke.exception.DukeException;

/**
 * A deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    /** The due date associated with the deadline task, by which the task should be completed */
    protected java.time.LocalDateTime dueDate;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of the deadline task.
     * @param dueDate The due date of the deadlined task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a deadline task with a specified completion status.
     *
     * @param description Description of the deadline task.
     * @param dueDate The due date of the deadlined task.
     * @param completion Whether the Deadline task has been completed.
     * @param completionDateTime The datetime when the task was marked completed.
     */
    public Deadline(String description, LocalDateTime dueDate,
                    boolean completion, @Nullable LocalDateTime completionDateTime) {
        super(description, completion, completionDateTime);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a deadline from a given string in the format: {description} /by {deadline}.
     *
     * @param cmd The string to construct the deadline from.
     * @return The constructed event based on the specifications of the command.
     * @throws DukeException If the command is invalid.
     */
    public static Deadline construct(String cmd) throws DukeException {
        if (!cmd.matches("(?i)^.+\\s/(by)\\s.+")) {
            String errorMessage = "hmm are you trying to edit a deadline?"
                    + " make sure the command is in the format: deadline {description}"
                    + " /by {due date}";
            throw new DukeException(errorMessage);
        }

        String[] sp = cmd.split("/(by)\\s", 2);
        String description = sp[0];
        String dateTimeString = sp[1];
        LocalDateTime datetime = DateTimeParse.parseDateTime(dateTimeString);
        return new Deadline(description, datetime);
    }

    /**
     * Returns a String representation of the due date datetime object associated to the
     * deadline task in EEEE, dd MMM yyyy HH:mm format.
     *
     * @return String representation of the due date in EEEE, dd MMM yyyy HH:mm format.
     */
    public String getDueDatetimeString() {
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        return dueDate.format(dayDateTimeFormatter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deadline edit(String userEditInput) throws DukeException {
        // this kind of stupidly edits the deadline by constructing a dummy deadline in
        // order to reuse the validation method, to look into whether there might
        // be better ways of going about this
        Deadline editedDeadline = construct(userEditInput);
        description = editedDeadline.description;
        dueDate = editedDeadline.dueDate;
        return this;
    }

    /**
     * Checks whether the deadline task is active on the specified date.
     * Will return true only if the task is overdue- meaning the due date has passed
     * (relative to the specified date), but it has not been completed,
     * or if the deadline falls on the specified date.
     *
     * @param date The date to check whether the task is active.
     * @return Whether the deadline task is active (overdue by, or due on the specified date).
     */
    @Override
    public boolean isActive(LocalDate date) {
        boolean isOverdue = dueDate.toLocalDate().isBefore(date) && !isDone;
        boolean isDueOnDate = dueDate.toLocalDate().equals(date);
        return isOverdue || isDueOnDate;
    }

    /**
     * Parses the Deadline into a savable string format, ready to be written to the hard disk.
     *
     * @return Savable string representation of the Deadline Task.
     */
    @Override
    public String toSaveFormat() {
        String savableCompletion = this.isDone ? "Y" : "N";
        // escape instances of deliminator in task description
        String escapedDescription = description.replace("|", "\\|");
        return String.format("D | %s | %s | %s | %s", savableCompletion,
                escapedDescription, dueDate, completionDateTime);
    }

    /**
     * Returns a string representation for the deadline task, prefixed with a [D],
     * followed by the deadline status, and the deadline description.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        String completionDescription = super.toString();
        String formattedDueDate = getDueDatetimeString();
        return String.format("[D]%s (by: %s)", completionDescription, formattedDueDate);
    }
}

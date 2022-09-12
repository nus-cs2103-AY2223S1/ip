package jude.task;

import static jude.Parser.DEFAULT_DATE_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jude.util.DateUtils;

// The idea to add a start and end time for an Event is adapted from
// https://github.com/nus-cs2103-AY2223S1/forum/issues/82
/**
 * An {@code Event} object is a Task which has a start time and an end time.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Creates a new {@code Event} object with a given description, whether it has been done, the
     * start time and the end time.
     *
     * @param description The description of the event.
     * @param isDone Whether the event is marked as done.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the task type code for an {@code Event} object, i.e. "E".
     *
     * @return "E".
     */
    @Override
    public String getTaskTypeCode() {
        return "E";
    }

    /**
     * Returns the String representation of the {@code Event} object, i.e.
     * a string in the format "[task type code][get status icon] description (at: time of event)".
     *
     * @return String representation of the {@code Event} object.
     */
    @Override
    public String toString() {
        return String.format("%s (from %s to %s)", super.toString(), start, end);
    }

    /**
     * Returns a String representation of the {@code Event} object which in a format convenient to
     * save and load files.
     *
     * The string returned is in the following format (with newlines in between components and in
     * the end):
     * Task Type Code, i.e. "E" for {@code Event} objects
     * Description
     * 1 if the task is done, and 0 otherwise
     * The time which the {@code Event} takes place
     *
     * @return The String representation of the {@code Event} object.
     */
    @Override
    public String toFileSaveString() {
        return String.format("%s%s\n%s\n", super.toFileSaveString(), start, end);
    }

    /**
     * Returns true if and only if {@code Event} has not started yet or is about to start, and is
     * at most the indicated number of seconds away from the start time.
     *
     * @param seconds Number of seconds time notice required.
     * @return True if and only if event to start within the number of seconds indicated in the
     *     parameter {@code seconds}.
     */
    @Override
    public boolean needsReminder(long seconds) {
        LocalDateTime startTime = LocalDateTime.parse(this.start, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
        long timeDifference = DateUtils.calculateTimeDifference(LocalDateTime.now(), startTime);
        return 0 <= timeDifference && timeDifference <= seconds;
    }
}

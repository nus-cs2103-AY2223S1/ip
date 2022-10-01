package functional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import technical.SaveLine;

/**
 * Class for tasks with starting and ending times.
 * @author Nicholas Patrick
 */
public class Event extends Task {
    private static final String EVENT_INFOTYPE = "event";
    private static final String EVENT_START_TIME_LABEL = "startTime";
    private static final String EVENT_END_TIME_LABEL = "endTime";
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    /**
     * Construct functional.Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Construct a task with a time interval from a SaveLine. If the argument
     * is invalid, an error may or may not be thrown.
     *
     * @param line The SaveLine containing necessary information.
     */
    public Event(SaveLine line) {
        super(line);
        startTime = LocalDateTime
            .parse(line.getValue(EVENT_START_TIME_LABEL), DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss"));
        endTime = LocalDateTime
            .parse(line.getValue(EVENT_END_TIME_LABEL), DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss"));
    }

    /**
     * Shows the event name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[E]%s (from %s to %s)", super.toString(),
            startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")),
            endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
    }

    /**
     * Turns the task into a SaveLine, so it's ready to be saved. Can also be
     * used to compare two tasks.
     *
     * @return A SaveLine with the data associated with the task.
     */
    @Override
    public SaveLine toData() {
        SaveLine ret = super.toData();
        ret.setInfoType(EVENT_INFOTYPE);
        ret.addKeyValue(EVENT_START_TIME_LABEL,
            startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
        ret.addKeyValue(EVENT_END_TIME_LABEL, endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
        return ret;
    }

    /**
     * Checks whether this is equal to another Object. If the other object is
     * not an Event, the return value will be false.
     *
     * @param rhs The right hand side of the comparison.
     * @return The boolean stating whether this and the argument are equal.
     */
    @Override
    public boolean equals(Object rhs) {
        if (!(rhs instanceof Event)) {
            return false;
        }
        Event rhsDeadline = (Event) rhs;
        return toData().equals(rhsDeadline.toData());
    }

    /**
     * Returns the important time of the event. If the event has started, this
     * value is the end of the event. Otherwise, it's the start of the event.
     *
     * @return The important time of the event.
     */
    @Override
    public LocalDateTime getTime() {
        return startTime.compareTo(LocalDateTime.now()) < 0 ? endTime : startTime;
    }
}

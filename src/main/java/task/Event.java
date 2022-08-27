package task;

import date.EventDateTime;

/**
 * Represents an event task
 *
 * @author Bryan Lim Jing Xiang
 */
public class Event extends Task {
    private final EventDateTime eventTiming;

    /**
     * {@inheritDoc}
     *
     * @param eventTiming Date and time of the event
     */
    public Event(String taskItem, EventDateTime eventTiming) {
        super(taskItem);
        this.eventTiming = eventTiming;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String eventTimingDisplay = String.format(" (at: %s)", this.eventTiming);
        return "[E]" + super.toString() + eventTimingDisplay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        int markedStatus = getIsMarked() ? 1 : 0;
        return String.format("E,%d,%s,%s\n", markedStatus, getTaskItem(), eventTiming.encode());
    }
}

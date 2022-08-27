package task;

import date.EventDateTime;

public class Event extends Task {
    private final EventDateTime eventTiming;

    public Event(String taskItem, EventDateTime eventTiming) {
        super(taskItem);
        this.eventTiming = eventTiming;
    }

    @Override
    public String toString() {
        String eventTimingDisplay = String.format(" (at: %s)", this.eventTiming);
        return "[E]" + super.toString() + eventTimingDisplay;
    }

    @Override
    public String encode() {
        int markedStatus = getIsMarked() ? 1 : 0;
        return String.format("E,%d,%s,%s\n", markedStatus, getTaskItem(), eventTiming.encode());
    }
}

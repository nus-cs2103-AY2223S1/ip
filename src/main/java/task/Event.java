package task;

import parser.DateTimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime eventDateTime;
    public Event(String description, boolean isDone, LocalDateTime eventDateTime) {
        super(description, isDone);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeParser.changeDateTimeFormat(eventDateTime) + ")";
    }
}
package domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event extends Task {
    private final LocalDateTime dateTime;

    private Event(String text, LocalDateTime dateTime) {
        super(text);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)",
                super.toString(),
                this.dateTime.format(formatter));
    }

    @Override
    public String exportString() {
        return String.format("%s%s%s",
                "E",
                super.exportString(),
                this.dateTime.format(formatter));
    }

    public static Event of(String text, LocalDateTime dateTime) {
        return new Event(text, dateTime);
    }

    public static Event of(String done, String text, LocalDateTime dateTime) {
        Event newEvent = Event.of(text, dateTime);
        if (Objects.equals(done, "1")) {
            newEvent.setComplete();
        }
        return newEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        if (!super.equals(o)) return false;
        return dateTime.equals(event.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateTime);
    }

    @Override
    public Boolean isBefore(LocalDateTime dateTime) {
        return this.dateTime.isBefore(dateTime);
    }

    @Override
    public Boolean isAfter(LocalDateTime dateTime) {
        return this.dateTime.isAfter(dateTime);
    }
}

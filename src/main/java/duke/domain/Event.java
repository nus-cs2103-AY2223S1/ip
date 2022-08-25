package duke.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Event.
 */
public class Event extends Task {

    private final LocalDateTime dateTime;

    private Event(String text, LocalDateTime dateTime) {
        super(text);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "[E] %s (at: %s)",
                super.toString(),
                this.dateTime.format(FORMATTER));
    }

    @Override
    public String exportString() {
        return String.format(
                "%s%s%s",
                "E",
                super.exportString(),
                this.dateTime.format(FORMATTER));
    }

    /**
     * Of event.
     *
     * @param text
     *            the text
     * @param dateTime
     *            the date time
     * @return the event
     */
    public static Event of(String text, LocalDateTime dateTime) {
        return new Event(text, dateTime);
    }

    /**
     * Of event.
     *
     * @param done
     *            the done
     * @param text
     *            the text
     * @param dateTime
     *            the date time
     * @return the event
     */
    public static Event of(String done, String text, LocalDateTime dateTime) {
        Event newEvent = Event.of(text, dateTime);
        if (Objects.equals(done, "1")) {
            newEvent.setComplete();
        }
        return newEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
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

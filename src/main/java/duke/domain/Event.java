package duke.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import duke.domain.task.Task;

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
        assert Objects.nonNull(text);
        assert Objects.nonNull(dateTime);
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
        assert Objects.nonNull(done);
        assert Objects.nonNull(text);
        assert Objects.nonNull(dateTime);
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

    /**
     * The compareTo function compares two events by their date and time.
     * If the dates are equal, then it compares them by their name.
     *
     * @param o
     *            Compare the datetime of this object with another
     * @return 1 if the current event is before the other event and -1 otherwise
     */
    public int compareTo(Task o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            if (this.dateTime == e.dateTime) {
                return super.compareTo(e);
            }
            return this.dateTime.isBefore(e.dateTime) ? -1 : 1;
        } else if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            if (this.dateTime == d.getDeadline()) {
                return super.compareTo(d);
            }
            return this.dateTime.isBefore(d.getDeadline()) ? -1 : 1;
        }
        return 1;
    }

    /**
     * @return the dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

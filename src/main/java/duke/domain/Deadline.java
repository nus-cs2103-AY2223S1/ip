package duke.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import duke.domain.task.Task;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    private final LocalDateTime deadline;

    private Deadline(String text, LocalDateTime deadline) {
        super(text);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D] %s (by: %s)",
                super.toString(),
                this.deadline.format(FORMATTER));
    }

    @Override
    public String exportString() {
        return String.format(
                "%s%s%s",
                "D",
                super.exportString(),
                this.deadline.format(FORMATTER));
    }

    /**
     * Of deadline.
     *
     * @param text
     *            the text
     * @param dateTime
     *            the date time
     * @return the deadline
     */
    public static Deadline of(String text, LocalDateTime dateTime) {
        assert Objects.nonNull(text);
        assert Objects.nonNull(dateTime);
        return new Deadline(text, dateTime);
    }

    /**
     * Of deadline.
     *
     * @param done
     *            the done
     * @param text
     *            the text
     * @param dateTime
     *            the date time
     * @return the deadline
     */
    public static Deadline of(
            String done,
            String text,
            LocalDateTime dateTime) {
        assert Objects.nonNull(done);
        assert Objects.nonNull(text);
        assert Objects.nonNull(dateTime);
        Deadline newDeadline = Deadline.of(text, dateTime);
        if (Objects.equals(done, "1")) {
            newDeadline.setComplete();
        }
        return newDeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Deadline deadline1 = (Deadline) o;
        return deadline.equals(deadline1.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deadline);
    }

    @Override
    public Boolean isBefore(LocalDateTime dateTime) {
        return this.deadline.isBefore(dateTime);
    }

    @Override
    public Boolean isAfter(LocalDateTime dateTime) {
        return this.deadline.isAfter(dateTime);
    }

    /**
     * The compareTo function compares two objects of the same type.
     *
     * @param o
     *            The Deadline to compare to
     * @return 1 if the current event is before the other event and -1 otherwise
     */
    public int compareTo(Task o) {
        if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            if (this.deadline == d.deadline) {
                return super.compareTo(d);
            }
            return this.deadline.isBefore(d.deadline) ? -1 : 1;
        } else if (o instanceof Event) {
            Event e = (Event) o;
            if (this.deadline == e.getDateTime()) {
                return super.compareTo(e);
            }
            return this.deadline.isBefore(e.getDateTime()) ? -1 : 1;
        }
        return 1;
    }

    /**
     * @return the deadline
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }
}

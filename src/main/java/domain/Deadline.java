package domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String text, LocalDateTime deadline) {
        super(text);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)",
                super.toString(),
                this.deadline.format(formatter));
    }

    @Override
    public String exportString() {
        return String.format("%s%s%s",
                "D",
                super.exportString(),
                this.deadline.format(formatter));
    }

    public static Deadline of(String text, LocalDateTime dateTime) {
        return new Deadline(text, dateTime);
    }

    public static Deadline of(String done, String text, LocalDateTime dateTime) {
        Deadline newDeadline = Deadline.of(text, dateTime);
        if (Objects.equals(done, "1")) {
            newDeadline.setComplete();
        }
        return newDeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deadline deadline1)) return false;
        if (!super.equals(o)) return false;
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
}

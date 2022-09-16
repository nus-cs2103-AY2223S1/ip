package duke.tasks;

import java.time.LocalDate;

public class Event extends Task {

    private final LocalDate localDate;

    public Event(String name, LocalDate date) {
        super(name);
        localDate = date;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + localDate.toString() + ")";
    }

    @Override
    public String toDataString() {
        return String.format("[E] | %d | %s | %s",
                isMarked() ? 1 : 0,
                getName(),
                localDate.toString());
    }
}

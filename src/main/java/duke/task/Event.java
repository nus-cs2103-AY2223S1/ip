package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

    public String getAt() {
        return this.at.toString();
    }

    public void changeDateFormat() {
        this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(at: " + at + ")";
    }
}

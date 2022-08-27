package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    public String toFileFormat() {
        String isDone = this.isDone ? "1" : "0";
        return "E | " + isDone + " | " + this.description + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event tmp = (Event) obj;
            return super.equals(tmp) && this.at.equals(tmp.at);
        }
        return false;
    }

}

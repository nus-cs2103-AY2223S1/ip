package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String toStorage() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + description + " | "
                + this.startDate + " | " + this.endDate + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.startDate.format(DateTimeFormatter.ofPattern("d MMM YYYY"))
                + " to " + this.endDate.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + ")";
    }
}

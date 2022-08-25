package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    public Event(String description, String timePeriod) {
        super(description);
        String[] str = timePeriod.split(" ");
        this.startDate = LocalDate.parse(str[0]);
        this.endDate = LocalDate.parse(str[1]);
    }

    @Override
    public String toStorage() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + description + " | "
                + this.startDate.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + " | "
                + this.endDate.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.startDate.format(DateTimeFormatter.ofPattern("d MMM YYYY"))
                + " to " + this.endDate.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + ")";
    }
}

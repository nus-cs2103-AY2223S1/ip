package duke.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, String dateTime) {
        super(description);
        this.date = LocalDate.parse(dateTime);
    }

    @Override
    public String toStorage() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + description + " | "
                + this.date.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + ")";
    }
}

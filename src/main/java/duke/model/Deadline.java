package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toStorage() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + description + " | " + this.date + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + ")";
    }
}

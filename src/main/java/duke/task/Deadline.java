package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDate date;
    public Deadline(String description, LocalDate date){
        super(description);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + "(" + this.date + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

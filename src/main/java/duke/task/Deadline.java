package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends DatedTask {
    public Deadline(String description, LocalDate by) throws DateTimeException {
        super(description, by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy") )+ ")";
    }
}
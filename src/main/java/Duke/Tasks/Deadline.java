package Duke.Tasks;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        String outputDate = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "D | " + super.toString() + " | " + outputDate;
    }
}

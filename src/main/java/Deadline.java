import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description, "D");
        this.date = date;
    }

    public Deadline(String description, String done,  LocalDate date) {
        super(description, done, "D");
        this.date = date;
    }

    public LocalDate getDateline() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.convertDateToString(date) + ")";
    }

    @Override
    public String toMemoryString() {
        return "D | " + super.toMemoryString() + " | " + this.by;
    }
}

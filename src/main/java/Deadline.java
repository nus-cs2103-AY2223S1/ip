import java.time.LocalDate;

public class Deadline extends Task {
    private String by;
    private LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    private String printDate() {
        return String.format("%s %d %d",
                this.date.getMonth().toString().substring(0, 3),
                this.date.getDayOfMonth(),
                this.date.getYear());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.printDate());
    }
}

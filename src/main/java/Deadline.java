import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    private String printDate() {
        return String.format("%s %d %d",
                this.by.getMonth().toString().substring(0, 3),
                this.by.getDayOfMonth(),
                this.by.getYear());
    }

    @Override
    public String stringify() {
        return String.format("%s | %s | %s", "D", super.stringify(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.printDate());
    }
}

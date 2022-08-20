import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public boolean isOn(LocalDate date) {
        return this.by.equals(date);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")));
    }
}

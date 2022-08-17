import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;
    Deadline(String description, LocalDate by) {
        super(description, by);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s%s(by: %s)", "[D]", super.toString(), this.by);
    }
}

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public LocalDate getDate() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
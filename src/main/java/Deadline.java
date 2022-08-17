import java.time.LocalDate;

public class Deadline extends Task {
    LocalDate by;
    Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE, by);
        this.by = by;
    }

    @Override
    String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("%s%s(by: %s)", "[D]", super.toString(), this.by);
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime by;
    Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE, by);
        this.by = by;
    }

    @Override
    String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s(by: %s)", "[D]", super.toString(), getBy());
    }
}

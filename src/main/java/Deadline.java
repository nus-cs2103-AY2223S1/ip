import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime dueDate;

    public Deadline(String name, String dueDateAsText) {
        super(name);
        this.dueDate = LocalDateTime.parse(dueDateAsText);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by %s)", super.toString(), this.dueDate.toString());
    }
}
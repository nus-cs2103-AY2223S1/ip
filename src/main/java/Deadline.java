import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    LocalDate deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

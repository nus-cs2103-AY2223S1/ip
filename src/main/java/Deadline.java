import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    Deadline(String name, String deadline, boolean status) {
        super(name, status);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    //Returns deadline of task
    public String getDeadline() {
        return deadline;
    }
}

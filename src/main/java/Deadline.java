import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDate deadline;

    public Deadline(String name, boolean done, String deadline) throws TaskNoNameException {
        super(name, done);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

}

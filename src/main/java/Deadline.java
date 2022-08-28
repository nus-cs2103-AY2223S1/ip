import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + LocalDate.parse(deadline).format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ")";
    }

    @Override
    public String toMemoryString() {
        return "D"
                + " | "
                + (this.isDone() ? "1" : "0")
                + " | "
                + this.getName()
                + " | "
                + this.deadline;
    }
}

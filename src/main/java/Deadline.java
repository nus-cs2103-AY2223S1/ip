import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String by;
    private LocalDate deadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadline = LocalDate.parse(by);
    }

    @Override
    public String toString(){
        return String.format("[D]" + super.toString() + " (by: %s)",
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}

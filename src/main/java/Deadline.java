import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate dueDate;
    protected LocalTime dueTime;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
        this.dueTime = null;
    }

    public Deadline(String description, String dueDate, String dueTime) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
        this.dueTime = LocalTime.parse(dueTime);
    }
    
    @Override
    public String toString() {
        if (dueTime == null) {
            return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + dueTime + ")";
        }
    }
}

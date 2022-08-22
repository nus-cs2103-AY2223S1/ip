import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime time;
    public Deadline(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.time.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ")";
    }
}

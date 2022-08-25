import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime by;

    public Deadline(String task, String by) {
        super(task);
        this.by = LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));;
    }

    protected String getDateTime() {
        return formatDateTime(by);
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDateTime() + ")";
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy kkmm")) + ")";
    }
}


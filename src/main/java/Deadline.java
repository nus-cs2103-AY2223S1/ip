import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, boolean done, LocalDateTime by) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[D][X]" : "[D][ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String dateFormatted = "(by: " + by.format(formatter) + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}

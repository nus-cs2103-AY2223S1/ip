import java.security.spec.ECField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        String color = (isDone ? ANSI_GREEN : ANSI_RED);
        return color + "[D]" + super.toString() + " (by: " + getDateTime(by) + ")" + ANSI_RESET;
    }

    @Override
    public String toFile() {
        int done = (isDone ? 1 : 0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return String.format("D | %d | %s | %s", done, description, dtf.format(by));
    }
}

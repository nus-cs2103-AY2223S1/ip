import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        try {
            this.at = LocalDateTime.parse(at.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        String color = (isDone ? ANSI_GREEN : ANSI_RED);
        return color + "[E]" + super.toString() + " (at: " + getDateTime(at) + ")" + ANSI_RESET;
    }

    @Override
    public String toFile() {
        int done = (isDone ? 1 : 0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return String.format("E | %d | %s | %s", done, description, dtf.format(at));
    }
}

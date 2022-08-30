import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;
    protected static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy hh:mma");

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(by, inputFormatter);
        } catch (Exception e) {
            throw new DukeException("Input your date and time in the format yyyy-MM-dd HHmm!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(outputFormatter) + ")";
    }
}
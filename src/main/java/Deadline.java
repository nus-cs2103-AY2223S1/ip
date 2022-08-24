import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime dateTime;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.dateTime = LocalDateTime.parse(by, formatter);
        if (description.isBlank()) {
            throw new DukeException("Take me seriouslyy :( What do you want to do?\n");
        }
        if (by.isBlank()) {
            throw new DukeException("When do you want to get it done??\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(formatter) + ")";
    }
}

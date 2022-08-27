import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description, Tag.D);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return description + " (" + DateTimeFormatter.ofPattern("MMM dd yyyy H:m").format(time) + ")";
    }
}

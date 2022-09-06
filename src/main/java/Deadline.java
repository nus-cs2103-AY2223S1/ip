import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected final LocalDate by;

    public Deadline(String description) {
        super(description.substring(9, description.indexOf('/') - 1));
        this.by = LocalDate.parse(description.substring(description.indexOf('/') + 3));
    }

    @Override
    public String fileFormat() {
        return String.format("deadline | %s | %s | %b", super.description, by, super.isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}

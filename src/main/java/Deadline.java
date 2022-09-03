import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description) {
        super(description.substring(9, description.indexOf('/') - 1));
        this.deadline = description.substring(description.indexOf('/') + 3);
    }

    @Override
    public String fileFormat() {
        return String.format("deadline | %s | %s | %b", super.description, deadline, super.isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadline + ")";
    }
}

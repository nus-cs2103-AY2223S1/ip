import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Deadline extends Task{
    private LocalDateTime dueDate;
    private final static DateTimeParser PARSER = new DateTimeParser();

    public Deadline(String taskName, String dueDate) throws DateTimeException {
        super(taskName);
        this.dueDate = PARSER.getDateTime(dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + PARSER.getDateTimeString(dueDate) + ")";
    }
}

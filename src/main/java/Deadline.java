import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String description, String date) throws DateTimeException {
        super(description);
        this.date = DateParser.parseToDate(date);
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: " + DateParser.dateToString(this.date) + ")";
    }

    @Override
    public String toCommand() {
        return TaskType.D + " | " + super.toCommand() + " /by " + DateParser.dateToCommand(this.date);
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskWithDate extends Task {

    protected LocalDateTime datetime;
    private DateTimeFormatter inDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter outDateTimeFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public TaskWithDate(String description, String datetime) {
        super(description);
        this.datetime = LocalDateTime.from(inDateTimeFormatter.parse(datetime));
    }

    String getDateTime() {
        return datetime.format(outDateTimeFormatter);
    }
}


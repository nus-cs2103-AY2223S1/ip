package piggy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskWithDate extends Task {

    protected LocalDateTime datetime;
    public static final DateTimeFormatter inDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter outDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public TaskWithDate(String description, String datetime) {
        super(description);
        this.datetime = LocalDateTime.from(inDateTimeFormatter.parse(datetime));
    }

    String getDateTime() {
        return datetime.format(outDateTimeFormatter);
    }

    public LocalDateTime getLocalDateTime() {
        return datetime;
    }
}


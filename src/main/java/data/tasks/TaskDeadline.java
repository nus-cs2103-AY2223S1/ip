package data.tasks;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;

public class TaskDeadline extends Task {

    private final LocalDateTime deadline;

    public TaskDeadline(String title, LocalDateTime deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
            deadline.equals(deadline.toLocalDate().atStartOfDay())
                ? deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                : deadline.format(
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
    }

}

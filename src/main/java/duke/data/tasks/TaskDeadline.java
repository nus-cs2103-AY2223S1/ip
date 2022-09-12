package duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TaskDeadline extends Task {
    private static final long serialVersionUID = 22L;

    private final LocalDateTime deadline;

    /**
     * Constructor for TaskDeadline class.
     *
     * @param title title of the deadline task.
     * @param deadline local date-time representing the deadline.
     */
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

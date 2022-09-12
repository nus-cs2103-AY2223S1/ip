package duke.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TaskEvent extends Task {
    private static final long serialVersionUID = 23L;

    private final LocalDateTime timing;

    /**
     * Constructor for TaskEvent class.
     *
     * @param title title of the event task.
     * @param timing local date-time representing the timing of the event.
     */
    public TaskEvent(String title, LocalDateTime timing) {
        super(title);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
            timing.equals(timing.toLocalDate().atStartOfDay())
                ? timing.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                : timing.format(
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
    }
}

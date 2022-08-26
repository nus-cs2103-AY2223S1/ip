import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private final LocalDateTime taskTime;
    private final String dateFormat;
    EventTask(String task, String duration, String dateFormat) throws EmptyTaskException, InvalidEventException {
        super(task);
        this.dateFormat = dateFormat;
        if (super.getName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(duration, DateTimeFormatter.ofPattern(dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidEventException(dateFormat);
        }
    }

    public String getTaskTime() {
        return (taskTime.getDayOfMonth() + " " +
                taskTime.getMonth() + " " +
                taskTime.getYear() + " | " +
                taskTime.getHour() + ":" +
                taskTime.getMinute());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + getTaskTime() + ")";
    }
}
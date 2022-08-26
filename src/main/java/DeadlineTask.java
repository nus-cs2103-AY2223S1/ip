import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDateTime taskTime;
    private final String dateFormat;
    DeadlineTask(String task, String date, String dateFormat) throws EmptyTaskException, InvalidDeadlineException {
        super(task);
        this.dateFormat = dateFormat;
        if (super.getName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(dateFormat);
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
        return "[D]" + super.toString() + " (by:" + getTaskTime() + ")";
    }
}
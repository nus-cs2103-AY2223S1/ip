import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private static final String TASK_TYPE = "D";
    private final LocalDateTime taskTime;
    private final String dateFormat;
    DeadlineTask(String taskName, String date, String dateFormat) throws EmptyTaskException, InvalidDeadlineException {
        super(taskName);
        this.dateFormat = dateFormat;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(dateFormat);
        }
    }

    DeadlineTask(String taskName, String date, boolean status, String dateFormat) throws EmptyTaskException, InvalidDeadlineException {
        super(taskName,  status);
        this.dateFormat = dateFormat;
        if (super.getTaskName().equals("")) {
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
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String getFormattedString() {
        return TASK_TYPE + " | " +
                (getStatus() ? 1 : 0) + " | " +
                getTaskName() + " | " +
                this.taskTime + "\n";
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by:" + getTaskTime() + ")";
    }
}
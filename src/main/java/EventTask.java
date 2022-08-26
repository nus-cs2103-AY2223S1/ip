import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private static final String TASK_TYPE = "E";
    private final LocalDateTime taskTime;
    private final String dateFormat;
    EventTask(String task, String taskTime, String dateFormat) throws EmptyTaskException, InvalidEventException {
        super(task);
        this.dateFormat = dateFormat;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern(dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidEventException(dateFormat);
        }
    }

    EventTask(String taskName, String taskTime, boolean status, String dateFormat) throws EmptyTaskException, InvalidEventException {
        super(taskName, status);
        this.dateFormat = dateFormat;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(taskTime);
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
        return "[" + TASK_TYPE + "]" + super.toString() + " (at:" + getTaskTime() + ")";
    }
}
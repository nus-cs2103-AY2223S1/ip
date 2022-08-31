package duke.taskmanager.task;

import duke.taskmanager.exceptions.EmptyTaskException;
import duke.taskmanager.exceptions.InvalidDeadlineException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private static final String TASK_TYPE = "D";
    private final LocalDateTime taskTime;
    private final String dateFormat;
    public DeadlineTask(String taskName, String taskTime, String dateFormat) throws EmptyTaskException, InvalidDeadlineException {
        super(taskName);
        this.dateFormat = dateFormat;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern(dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(dateFormat);
        }
    }

    public DeadlineTask(String taskName, String taskTime, boolean status, String dateFormat) throws EmptyTaskException, InvalidDeadlineException {
        super(taskName,  status);
        this.dateFormat = dateFormat;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(taskTime);
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(dateFormat);
        }
    }

    public String getTaskTime() {
        return (taskTime.getDayOfMonth() + " " +
                taskTime.getMonth() + " " +
                taskTime.getYear() + " | " +
                taskTime.getHour() + ":" +
                String.format("%02d", taskTime.getMinute()));
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String getFormattedString() {
        return TASK_TYPE + "<>" +
                (getStatus() ? 1 : 0) + "<>" +
                getTaskName() + "<>" +
                this.taskTime + "\n";
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by:" + getTaskTime() + ")";
    }
}
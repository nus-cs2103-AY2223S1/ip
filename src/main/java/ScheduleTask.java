import java.time.LocalDate;
import java.time.LocalTime;

public abstract class ScheduleTask extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public ScheduleTask(String description, String dateTime) {
        super(description);
        String[] dateAndTime = dateTime.split(" ");
        date = DateTimeHandler.formatDate(dateAndTime[0]);
        time = DateTimeHandler.formatTime(dateAndTime[1]);
    }

    protected String showDateTime() {
        return date.format(DateTimeHandler.returnDateFormat) + " " + time.format(DateTimeHandler.returnTimeFormat);
    }
}

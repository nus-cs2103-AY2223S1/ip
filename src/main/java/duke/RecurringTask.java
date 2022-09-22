package duke;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * Represents a recurring task.
 */
public class RecurringTask extends Task {

    protected LocalDateTime time;
    protected String dayString;
    protected String timeString;

    /**
     * Constructs a <code>RecurringTask</code> task.
     *
     * @param description Description of the recurring task.
     * @param day Day of the recurring task.
     * @param time Time of the recurring task in "HHmm" format.
     * @param isDone Indicator whether the task has been done or not.
     */
    public RecurringTask(String description, String day, String time, boolean isDone) {
        super(description, isDone);
        this.dayString = day.toUpperCase();
        this.timeString = time;
        this.time = getUpcomingDateTime(dayString, timeString);
    }

    /**
     * Constructs a <code>RecurringTask</code> task that is 7 days following the given <code>RecurringTask</code>.
     *
     * @param task A <code>RecurringTask</code>.
     */
    public RecurringTask(RecurringTask task) {
        super(task.description, false);
        this.time = task.time.plusDays(7);
        this.dayString = task.dayString;
        this.timeString = task.timeString;
    }

    /**
     * Returns the upcoming <code>LocalDateTime</code> with the specified day and time.
     *
     * @param day The day of the <code>LocalDateTime</code> object that will be returned.
     * @param time The time of the <code>LocalDateTime</code> object that will be returned.
     * @return An upcoming <code>LocalDateTime</code> with the specified day and time from when the function is called.
     */
    private LocalDateTime getUpcomingDateTime(String day, String time) {
        LocalDateTime dt = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.valueOf(day)));
        dt = dt.withHour(Integer.parseInt(time.substring(0, 2)));
        dt = dt.withMinute(Integer.parseInt(time.substring(2, 4)));
        return dt;
    }

    /**
     * Returns the hour and minutes of this <code>RecurringTask</code> in a <code>String</code> in "HHmm" format.
     *
     * @return <code>String</code> representation of the hour and minutes of this <code>RecurringTask</code>.
     */
    public String getTime() {
        int len = timeString.length();
        return timeString.substring(len - 4);
    }

    /**
     * Returns the <code>String</code> representation of this <code>RecurringTask</code>.
     *
     * @return <code>String</code> representation of this <code>RecurringTask</code>.
     */
    @Override
    public String toString() {
        return "[R]" + super.toString() + " (every: " + time.getDayOfWeek() + " at: " + this.getTime() + ")";
    }

    /**
     * Returns the <code>String</code> representation of this <code>RecurringTask</code> in the format to be stored
     * in the local storage.
     *
     * @return <code>String</code> representation of this <code>RecurringTask</code> in the format to be stored
     *     in the local storage.
     */
    public String toStorageFormat() {
        char done = isDone ? '1' : '0';
        return "R" + " | " + done + " | " + this.description + " | " + this.dayString + " | " + this.timeString + "\n";
    }

    @Override
    public boolean isRecurring() {
        return true;
    }
}

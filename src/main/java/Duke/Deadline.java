package Duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline with type Task
 */
public class Deadline extends Task{

    protected String by;
    protected String date;
    protected String time;
    protected String a, b;

    /**
     * Constructor for deadline
     *
     * @param name of task
     * @param date specific date of task in format dd/mm/yyyy
     * @param time in format hhmm(24-hour clock)
     */
    public Deadline(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    /**
     * Change date to specific format (MMM dd yyy) and get the day of the task
     */
    public void dateProcess() {
        if(date.length() == 9) {
            date = "0" + date;
        }
        LocalDate d1 = LocalDate.parse(date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2));
        by = d1.getDayOfWeek().toString(); // -> SUNDAY
        a = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy")); // -> Dec 27 2001
    }

    /**
     * Change the time to hh:mm am/pm (12-hour clock)
     */
    public void timeProcess() {
        String hour = time.substring(0, 2);
        String min = time.substring(2);
        LocalTime t1 = LocalTime.parse(hour + ":" + min);
        b = t1.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * {@inheritDoc}
     * @return string of formatted text in file
     */
    @Override
    public String fileFormat() {
        return "D|" + super.fileFormat() + "|" + by + "|" + date + "|" + time;
    }

    /**
     *{@inheritDoc}
     * @return string of desired format
     */
    @Override
    public String toString() {
        dateProcess();
        timeProcess();
        return "[D]" + super.toString() + " (by: " + by + ", " + a + ", " + b + ")";
    }
}

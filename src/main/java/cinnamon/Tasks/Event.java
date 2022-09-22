package cinnamon.Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event with type Task
 */
public class Event extends Task {

    protected String at;
    protected String day;
    protected String date;
    protected String time;
    protected String a, b;

    /**
     * Constructor of event
     *
     * @param name of task
     * @param at location of task
     * @param date specific date of task in format yyyy-mm-dd
     * @param time in format hhmm(24-hour clock)
     */
    public Event(String name, String at, String date, String time) {
        super(name);
        this.at = at;
        this.time = time;
        this.date = date;
    }

    /**
     * Change date to specific format (MMM dd yyy) and get the day of the task
     */
    public void dateProcess() {
        LocalDate d1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        day = d1.getDayOfWeek().toString(); // -> SUNDAY
        a= d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy")); // -> Dec 27 2001
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
        return "E|" + super.fileFormat() + "|" + at + "|" + date + "|" + time;
    }

    /**
     *{@inheritDoc}
     * @return string of desired format
     */
    @Override
    public String toString() {
        dateProcess();
        timeProcess();
        return "[E]" + super.toString() + " (at: " + at + ", " + day + ", " + a + ", " + b + ")";
    }
}

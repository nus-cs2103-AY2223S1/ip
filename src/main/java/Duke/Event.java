package Duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{

    protected String at;
    protected String day;
    protected String date;
    protected String time;
    protected String a, b;

    public Event(String name, String at, String date, String time) {
        super(name);
        this.at = at;
        this.time = time;
        this.date = date;
    }

    public void dateProcess() {
        if(date.length() == 9) {
            date = "0" + date;
        }
        LocalDate d1 = LocalDate.parse(date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2));
        day = d1.getDayOfWeek().toString(); // -> SUNDAY
        a= d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy")); // -> Dec 27 2001
    }

    public void timeProcess() {
        String hour = time.substring(0, 2);
        String min = time.substring(2);
        LocalTime t1 = LocalTime.parse(hour + ":" + min);
        b = t1.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public String fileFormat() {
        return "E" + super.fileFormat() + "|" + at + "|" + day + "|" + date + "|" + time;
    }

    @Override
    public String toString() {
        dateProcess();
        timeProcess();
        return "[E]" + super.toString() + " (at: " + at + ", " + day + ", " + a + ", " + b + ")";
    }
}

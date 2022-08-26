package Duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String by;
    protected String date;
    protected String time;
    protected String a, b;

    public Deadline(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    public Deadline(Task task) {
        super(task.name);
    }

    public void dateProcess() {
        if(date.length() == 9) {
            date = "0" + date;
        }
        LocalDate d1 = LocalDate.parse(date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2));
        by = d1.getDayOfWeek().toString(); // -> SUNDAY
        a = d1.format(DateTimeFormatter.ofPattern("MMM dd YYYY")); // -> Dec 27 2001
    }

    public void timeProcess() {
        String hour = time.substring(0, 2);
        String min = time.substring(2);
        LocalTime t1 = LocalTime.parse(hour + ":" + min);
        b = t1.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public String fileFormat() {
        return "D|" + super.fileFormat() + "|" + by + "|" + a + "|" + b;
    }

    @Override
    public String toString() {
        dateProcess();
        timeProcess();
        return "[D]" + super.toString() + " (by: " + by + ", " + a + ", " + b + ")";
    }
}

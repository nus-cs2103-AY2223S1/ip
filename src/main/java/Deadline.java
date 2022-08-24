import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime date; //'2/12/2019 1800'

    public Deadline(String description, String by) {
        super(description);
        this.date = dateTimeParser(by);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
        return "[D]" + super.toString() + " (by: " + date.format(formatter) + ")";
    }

    public static LocalDateTime dateTimeParser(String time) {
        String[] timing = time.split(" ", 2);
        String[] dayMonYr = timing[0].split("/", 3);
        int hr = Integer.valueOf(timing[1].substring(0,2));
        int minute = Integer.valueOf(timing[1].substring(2));
        return LocalDateTime.of(Integer.valueOf(dayMonYr[2]),
                                Integer.valueOf(dayMonYr[1]),
                                Integer.valueOf(dayMonYr[0]),
                                hr, minute);
    }
}
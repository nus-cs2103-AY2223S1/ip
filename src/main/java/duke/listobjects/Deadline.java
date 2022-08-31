package duke.listobjects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends ListObject{

    String doBy;

    public Deadline (String task, int status, String doBy) {
        super(task, status);
        this.doBy = doBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(doBy) + ")";
    }

    public String formatDateTime(String txt) {

        String[] words = txt.split(" ");
        String date = words[0];
        String time = words[1];

        //format date of form yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deadline = LocalDate.parse(date, formatter);
        DateTimeFormatter formatNew = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String dateNew = deadline.format(formatNew);

        //format time of form HH:mm (24h clock)
        LocalTime deadlineTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
        String timeNew = deadlineTime.format(DateTimeFormatter.ISO_LOCAL_TIME);

        return dateNew + " at " + timeNew;
    }

}

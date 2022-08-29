package duke.listobjects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends ListObject{

    String eventTime;

    public Event(String task, int status) {
        super(task, status);
    }

    public Event(String task, int status, String eventTime) {
        super(task, status);
        this.eventTime = eventTime;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + formatDateTime(eventTime) + ")";
    }

    public String formatDateTime(String txt){

        String[] words = txt.split(" ");
        String date = words[0];
        String start = words[1];
        String end = words[2];

        //formate date of form yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deadline = LocalDate.parse(date, formatter);
        DateTimeFormatter formatNew = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String dateNew = deadline.format(formatNew);

        //format time of form HH:mm (24h clock)
        LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ISO_LOCAL_TIME);
        String timeStart = startTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ISO_LOCAL_TIME);
        String timeEnd = endTime.format(DateTimeFormatter.ISO_LOCAL_TIME);

        return dateNew + " from: " + timeStart +  " to: " + timeEnd;
    }
}

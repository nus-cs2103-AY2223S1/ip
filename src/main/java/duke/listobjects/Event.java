package duke.listobjects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event which is a Listobject with a task along with the event date, start and end time
 */
public class Event extends ListObject {

    String eventTime;

    /**
     * Constructs an Event object with given task decription and status and unspecified time
     * @param task String representing task decription
     * @param status int with value 1 if event is completed and 0 otherwise
     */
    public Event(String task, int status) {
        super(task, status);
        this.eventTime = "unspecified";
    }

    /**
     * Constructs an Event object with give task description, status and time
     * @param task String representing task description
     * @param status int with value 1 if event is completed and 0 otherwise
     * @param eventTime String representing date, start and end times of event
     */
    public Event(String task, int status, String eventTime) {
        super(task, status);
        this.eventTime = eventTime;
    }

    /**
     * Returns String representation of Event object
     * @return String representing Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatDateTime(eventTime) + ")";
    }

    /**
     * Reads String representing event time and returns it in alternate format
     * @param txt String representing event time (date, start time, end time) in the format yyyy-MM-dd HH:mm HH:mm
     * @return String representing event time in format MMM dd yyyy HH:mm HH:mm as date, start and end times
     */

    public String formatDateTime(String txt) {


        String[] words = txt.split(" ");
        String date = words[0];
        String start = words[1];
        String end = words[2];

        //format date of form yyyy-MM-dd
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

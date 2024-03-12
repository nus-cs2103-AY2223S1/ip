package duke.task;

import java.time.format.DateTimeParseException;

import duke.exception.DateNotFoundException;
import duke.tools.DateTime;

public class Event extends Task {
    private DateTime dateTime;

    private Event(String taskText, DateTime dateTime) {
        super(taskText.trim());
        this.dateTime = dateTime;
    }

    public static Event of(String taskText, String source) throws DateNotFoundException, DateTimeParseException {
        if (source.contentEquals("FILE")) {
            String[] detailArr = taskText.replace('|', '/').split("/", 3);
            DateTime date = new DateTime(DateTime.convertDate(detailArr[2].trim()));
            Event event = new Event(detailArr[1], date);
            if (detailArr[0].contains("X")) {
                event.markAsDone();
            }
            return event;
        } else {
            if (!taskText.contains("/")) {
                throw new DateNotFoundException(taskText);
            }

            String eventDetails = taskText.substring(0, taskText.indexOf('/'));
            String dateTime = taskText.substring(taskText.indexOf('/') + 3);
            DateTime date = new DateTime(dateTime.trim());
            return new Event(eventDetails, date);
        }
    }

    @Override
    public String toString() {
        //ui: (at: date)
        return "E" + super.toString() + " | " + getDateTime();
    }

    public String getDateTime() {

        return dateTime.getDate();
    }

    public DateTime getParsedDate() {

        return this.dateTime;
    }
}

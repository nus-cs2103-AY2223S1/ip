package duke.task;

import duke.exception.DateNotFoundException;
import duke.tools.DateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String dateTime;

    private Deadline(String taskText, DateTime dateTime) {
        super(taskText.trim());
        this.dateTime = dateTime.getDate();
    }

    public static Deadline of(String taskText, String source) throws DateNotFoundException, DateTimeParseException {
        if (source.contentEquals("FILE")) {
            String[] detailArr = taskText.replace('|', '/').split("/", 3);
            DateTime date = new DateTime(detailArr[2]);
            Deadline deadline = new Deadline(detailArr[1], date);
            if (detailArr[0].contains("X")) {
                deadline.done();
            }
            return deadline;
        } else {
            if (!taskText.contains("/")) {
                throw new DateNotFoundException(taskText);
            }
            String eventDetails = taskText.substring(0, taskText.indexOf('/'));
            String dateTime = taskText.substring(taskText.indexOf('/')+3);
            DateTime date = new DateTime(dateTime.trim());
            return new Deadline(eventDetails, date);
        }
    }

    @Override
    public String toString(){
        //ui : (by: date)
        return "D" + super.toString() + " | " + dateTime;
    }
}
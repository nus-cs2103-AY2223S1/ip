package duke.task;

import java.time.format.DateTimeParseException;

import duke.exception.DateNotFoundException;
import duke.tools.DateTime;

public class Deadline extends Task {
    private DateTime dateTime;

    private Deadline(String taskText, DateTime dateTime) {
        super(taskText.trim());
        this.dateTime = dateTime;
    }

    /**
     * Factory method to create a deadline object from user input or file read.
     * @param taskText Details of the task.
     * @param source source of the input, UI or FILE.
     * @return Returns a Deadline object.
     * @throws DateNotFoundException Exception thrown when deadline object does not contain a date.
     * @throws DateTimeParseException Exception thrown when date formatting is wrong.
     */
    public static Deadline of(String taskText, String source) throws DateNotFoundException, DateTimeParseException {
        assert ! source.isEmpty() : "Source is not empty";

        if (source.contentEquals("FILE")) {

            String[] detailArr = taskText.replace('|', '/').split("/", 3);

            assert detailArr[2] != null : "Deadline date exists";
            DateTime date = new DateTime(DateTime.convertDate(detailArr[2].trim()));
            Deadline deadline = new Deadline(detailArr[1], date);

            if (detailArr[0].contains("X")) {
                deadline.markAsDone();
            }

            return deadline;
        } else {
            if (!taskText.contains("/")) {
                throw new DateNotFoundException(taskText);
            }

            String eventDetails = taskText.substring(0, taskText.indexOf('/'));
            String dateTime = taskText.substring(taskText.indexOf('/') + 3);
            DateTime date = new DateTime(dateTime.trim());
            return new Deadline(eventDetails, date);
        }
    }

    /**
     * {@inheritdocs}
     * @return {@inheritdocs}
     */
    @Override
    public String toString() {
        //ui : (by: date)
        return "D" + super.toString() + " | " + getDateTime();
    }

    public String getDateTime() {

        return dateTime.getDate();
    }

    public DateTime getParsedDate() {

        return this.dateTime;
    }
}

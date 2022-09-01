package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is specialised Task with a date and time as deadline
 */
public class Event extends Task{
    private String dateTime = "";
    private LocalDateTime dateTimeProper;

    /**
     * Class Constructor using description of task and dateTime
     * @param taskDescription description of task
     * @param dateTime date that task is due
     */
    public Event(String taskDescription, String dateTime) throws DukeException {
        super(taskDescription.replace("event ", ""));
        this.dateTime = dateTime;
        try {
            this.dateTimeProper = LocalDateTime.parse(dateTime);
        } catch (Exception e) {
            throw new DukeException("Date and Time of your event must be in the following format: YYYY-MM-DDTHH:MM");
        }
    }

    /**
     * Class Constructor using description of task and dateTime
     * @param taskDescription description of task
     * @param dateTime date that task is due
     * @param isCompleted completion status of task
     */
    public Event(String taskDescription, String dateTime, boolean isCompleted) throws DukeException{
        super(taskDescription, isCompleted);
        this.dateTime = dateTime;
        try {
            this.dateTimeProper = LocalDateTime.parse(dateTime);
        } catch (Exception e) {
            throw new DukeException("Date and Time of your event must be in the following format: YYYY-MM-DDTHH:MM");
        }
    }

    /**
     * Returns the String format of Task for display in UI
     * @return String of task
     */
    @Override
    protected String returnDescription() {
        String formattedDate = this.dateTimeProper.format(DateTimeFormatter.ofPattern("MMM dd yyyy @ HH:mm"));
        return "[E]" + super.returnDescription() + " (at: " + formattedDate + ")";
    }

    /**
     * Returns the String format of Task for saving to file
     * @return String of task
     */
    @Override
    protected String toWriteFile() {
        return "E , " + super.toWriteFile() + " , " + this.dateTime;
    }
}

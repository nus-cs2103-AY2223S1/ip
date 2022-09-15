package duke.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import duke.DukeException;

/**
 * Event is specialised Task with a date and time as deadline
 */
public class Event extends Task {
    private String dateTime = "";
    private LocalDateTime properDateTime;

    /**
     * Class Constructor using description of task and dateTime
     * @param taskDescription description of task
     * @param dateTime date that task is due
     */
    public Event(String taskDescription, String dateTime) throws DukeException {
        super(taskDescription.replace("event ", ""));
        this.dateTime = dateTime;
        try {
            this.properDateTime = LocalDateTime.parse(dateTime);
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
            this.properDateTime = LocalDateTime.parse(dateTime);
        } catch (Exception e) {
            throw new DukeException("Date and Time of your event must be in the following format: YYYY-MM-DDTHH:MM");
        }
    }

    public LocalDateTime getDate() {
        return this.properDateTime;
    }
    /**
     * Returns the String format of Task for display in UI
     * @return String of task
     */
    @Override
    public String returnDescription() {
        String formattedDate = this.properDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy @ HH:mm"));
        return "[E]" + super.returnDescription() + " (at: " + formattedDate + ")";
    }

    /**
     * Returns the String format of Task for saving to file
     * @return String of task
     */
    @Override
    public String toWriteFile() {
        return "E , " + super.toWriteFile() + " , " + this.dateTime;
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of task that contains a date that the event is at.
 */
public class Event extends Task {

    /**
     * Time of the event.
     */
    private String time = null;
    /**
     * Date of the event in LocalDate type.
     */
    private LocalDate date;

    private boolean isConverterdTime = false;

    public Event(String name) {
        super(name);
    }

    /**
     * Returns the tag for the type of event.
     *
     * @return The 'E' tag for event task.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return Event string representation.
     */
    @Override
    public String toString() {
        return "[E] " + (super.isCompleted() ? "[X] " : "[ ] ") + super.getTaskName() + "(at: " + this.getDate() + ")";
    }

    public void setTime(String time) {
        this.time = time;

    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + (this.time == null ? "" : ", " + this.getTime());
    }

    public String getDateFormat() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + (this.time == null ? "" : " " + this.getTime());
    }

    public String getTime() {
        if(this.time == null) {
            return "";
        }

        if (isConverterdTime) {
            return this.time;
        }

        String hh = this.time.substring(0,2);
        String mm = this.time.substring(2,4);

        if (Integer.parseInt(hh) > 12) {
            hh = String.valueOf(Integer.parseInt(hh) - 12);
            return hh + ":" + mm +"pm";
        }
        else {
            return Integer.parseInt(hh) == 0
                    ? "12:" + mm + "am"
                    : hh + ":" + mm + "am";
        }
    }

    public void setIsConvertedTime() {
        isConverterdTime = true;
    }

}

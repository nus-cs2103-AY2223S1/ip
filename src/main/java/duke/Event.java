package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /**
     * Time of the event.
     */
    private String time = null;
    /**
     * Date of the event in LocalDate type.
     */
    private LocalDate date;

    public Event(String name) {
        super(name);
    }

    /**
     * Returns the tag for the type of event.
     * @return The 'E' tag for event task.
     */
    @Override
    public String getTaskType() {
        return "E";
    }


    /**
     * Returns the string representation of the event task.
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
        String hh = this.time.substring(0,2);
        String mm = this.time.substring(2,4);

        if (Integer.parseInt(hh) > 12) {
            hh = String.valueOf(Integer.parseInt(hh) - 12);
            return hh + ":" + mm +"pm";
        }
        else {
            if (Integer.parseInt(hh) == 0) {
                return "12:" + mm +"am";
            } else {
                return hh + ":" + mm +"pm";
            }
        }
    }

}

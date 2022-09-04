package duke.tasks;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, which has the task in String, if done as boolean
 * and a timing as date to do the task by
 */
public class Event extends Task {
    private final LocalDateTime timing;

    public Event(String input, String timing) {
        super(input, "");
        this.timing = getTime(timing);
    }

    public Event(String input, boolean done, String timing) {
        super(input, done, "");
        this.timing = getTime(timing);
    }

    public Event(String input, boolean done, LocalDateTime timing) {
        super(input, done, "");
        this.timing = timing;
    }

    /**
     * @return date, in format of day month year hour:mins, with month in word spelling
     */
    @Override
    public String getTiming() {
        int day = this.timing.getDayOfMonth();
        Month month = this.timing.getMonth();
        int year = this.timing.getYear();
        int hour = this.timing.getHour();
        int min = this.timing.getMinute();
        return(String.format("%s %s %s %02d:%02d", day, month, year, hour, min));
    }

    /**
     * @return new event object, with deadline toggled to True
     */
    public Event markDone() {
        return new Event(this.getVal(), true, this.timing);
    }

    /**
     * @return new event object, with deadline toggled to False
     */
    public Event markUndone() {
        return new Event(this.getVal(), false, this.timing);
    }

    /**
     * @return string of method as [E][deadline] task (date) format
     */
    @Override
    public String toString() {
        if(this.getDone()) {
            return (String.format("[E][X] %s (%s)", this.getVal(), this.getTiming()));
        }
        else {
            return String.format("[E][ ] %s (%s)", this.getVal(), this.getTiming());
        }
    }

    /**
     * @return string format D | done | task | date, as specified in duke.txt
     */
    @Override
    public String toText() {
        return String.format("E | %s | %s | %s", this.getDone() ? 1 : 0, this.getVal(), getTiming());
    }

    private LocalDateTime getTime(String str) {
        //from stackoverflow
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    /**
     * @param date the input day
     * @return boolean if task is on same day
     */
    public boolean sameDay(LocalDateTime date) {
        return (this.timing.getDayOfMonth() == date.getDayOfMonth() &&
                this.timing.getMonth().equals(date.getMonth()) &&
                this.timing.getYear() == date.getYear());

    }
}

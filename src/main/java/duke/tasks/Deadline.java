package duke.tasks;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline, which has the task in String, if done as boolean
 * and a deadline as date to do the task by
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String input, String deadline) {
        super(input, "", "D");
        this.deadline = getTime(deadline);
    }

    public Deadline(String input, boolean done, String deadline) {
        super(input, done, "", "D");
        this.deadline = getTime(deadline);
    }

    public Deadline(String input, boolean done, LocalDateTime deadline) {
        super(input, done, "", "D");
        this.deadline = deadline;
    }

    /**
     * @return new deadline object, with deadline toggled to True
     */
    public Deadline markDone() {
        return new Deadline(this.getVal(), true, this.deadline);
    }

    /**
     * @return new deadline object, with deadline toggled to False
     */
    public Deadline markUndone() {
        return new Deadline(this.getVal(), false, this.deadline);
    }

    /**
     * @return date, in format of day month year hour:mins, with month in word spelling
     */
    @Override
    public String getTiming() {
        int day = this.deadline.getDayOfMonth();
        Month month = this.deadline.getMonth();
        int year = this.deadline.getYear();
        int hour = this.deadline.getHour();
        int min = this.deadline.getMinute();
        String format = "%s %s %s %02d:%02d";
        return(String.format(format, day, month, year, hour, min));
    }

    public LocalDateTime getDateTimeObject() {
        return this.deadline;
    }

    /**
     * @return string of method as [D][deadline] task (date) format
     */
    @Override
    public String toString() {
        String format;
        if(this.getDone()) {
            format = "[D][X] %s (%s)";
        }
        else {
            format = "[D][ ] %s (%s)";
        }
        return (String.format(format, this.getVal(), this.getTiming()));
    }

    /**
     * @return string format D | done | task | date, as specified in duke.txt
     */
    @Override
    public String toText() {
        var isDone = this.getDone() ? 1 : 0;
        String format = "D | %s | %s | %s";
        return String.format(format, isDone, this.getVal(), this.getTiming());
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
        return (this.deadline.getDayOfMonth() == date.getDayOfMonth() &&
                this.deadline.getMonth().equals(date.getMonth()) &&
                this.deadline.getYear() == date.getYear());

    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that extends Task class.
 */
public class Event extends Task{
    protected String timing;
    protected String date;
    protected boolean isDone;

    /**
     * Marks as a task as done and displays output messages.
     *
     * @param description name of the event.
     * @param date date that the event will take place.
     * @param timing time that the event has taken place.
     */
     public Event(String description, String date, String timing,boolean isDone) {
         super(description);
         this.timing = timing;
         this.date  = date;
         this.isDone = isDone;
     }
    /**
     * returns the String value of the date of the event.
     * @return String value of date.
     */
    @Override
    public String date() {
        return this.date;
    }

    /**
     * returns the String value of the timing of the event.
     * @return String value of the timing.
     */
    @Override
    public String timing() {
        return this.timing;
    }

    /**
     * tells us if a particular task is done or not.
     * @return cross represents done, otherwise blank space means not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    /**
     * marks a specific task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * marks a specific task object as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * returns String representation.
     * @return String representation of Task
     */
     @Override
     public String toString() {
         LocalDate d1 = LocalDate.parse(this.date);
         return "[E]" + "[" + this.getStatusIcon() + "]" +  super.toString() + " (at: " +
                 d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.timing +  ")";
     }
}

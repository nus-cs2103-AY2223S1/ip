package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the event task.
 */
public class Event extends Task{
    protected LocalTime timing;
    protected LocalDate date;
    protected boolean isDone;

    /**
     * Creates an event object.
     *
     * @param description name of the event.
     * @param date date that the event will take place.
     * @param timing time that the event has taken place.
     * @param isDone boolean value signifying if the task is done or not.
     */
     public Event(String description, LocalDate date, LocalTime timing, boolean isDone) {
         super(description);
         this.timing = timing;
         this.date  = date;
         this.isDone = isDone;
     }

    /**
     * Returns the String value of the date of the event.
     *
     * @return String value of date.
     */
    @Override
    public String date() {
        return this.date.toString();
    }

    /**
     * Returns the String value of the timing of the event.
     *
     * @return String value of the timing.
     */
    @Override
    public String timing() {
        return this.timing.toString();
    }

    /**
     * Returns status icon of whether the task is done or not.
     *
     * @return cross represents done, otherwise blank space means not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    /**
     * Marks a specific task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a specific task object as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

     /**
      * Returns String representation.
      *
      * @return String representation of Task.
      */
     @Override
     public String toString() {

             return "[E]" + "[" + this.getStatusIcon() + "]" + super.toString() + " (at: " +
                     this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.timing.toString() + ")";
         }
     }


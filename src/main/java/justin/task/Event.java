package justin.task;

import justin.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a task that has a date and a time
 * at which it happens.
 * @author Justin Cheng.
 */
public class Event extends Task {
    protected LocalDate at;
    protected LocalTime time;
    protected LocalDateTime dateAndTime;

    /**
     * Constructor for the Event class
     * @param description The description of the event.
     * @param isDone The boolean value of whether the event is done.
     * @param at The date at which it happens in String.
     * @param time The time at which it happens in String.
     */
    public Event(String description, boolean isDone, String at, String time) {
        super(description, isDone);
        this.at = LocalDate.parse(at);
        this.time = LocalTime.parse(time);
        setDateAndTime(this.at, this.time);
    }

    /**
     * Returns a String to represent an Event task.
     * @return String representation of Event task.
     */
    @Override
    public String toString() {
        return "E | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time.format(DateTimeFormatter.ofPattern("hhmma"));
    }

    /**
     * Sets the date and time of a particular Event.
     * @param date The date of the Event.
     * @param time The time of the Event.
     */
    public void setDateAndTime(LocalDate date, LocalTime time) {
        dateAndTime = date.atTime(time);
    }

    /**
     * Returns the date and time of the Event in
     * LocalDateTime.
     * @return The LocalDateTime of the Event.
     */
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Checks whether the Event overlaps with any other
     * Events in the TaskList.
     * @param list The TaskList containing other tasks.
     * @return Returns true if there are overlaps.
     * Returns false if there are no overlaps.
     */
    public boolean isOverlap(TaskList list) {
        ArrayList<Task> tasks = list.getTasks();
        boolean result = false;
        for (Task t: tasks) {
            if (t instanceof Event) {
                Event curr = (Event) t;
                result |= isEqualDateAndTime(curr);
            }
        }
        return result;
    }

    /**
     * Checks if two Event objects are of the same
     * date and time.
     * @param anotherEvent Another Event object that
     *                     we are comparing against.
     * @return Returns true if the two Events have
     * the same date and time.
     * Returns false otherwise.
     */
    public boolean isEqualDateAndTime(Event anotherEvent) {
        if (getDateAndTime().equals(anotherEvent.getDateAndTime())) {
            return true;
        }
        return false;
    }
}

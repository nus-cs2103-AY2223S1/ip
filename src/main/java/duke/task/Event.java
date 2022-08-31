package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Event task class.
 */
public class Event extends Task{

    private  LocalDate date;


    /**
     * Constructor of event task.
     *
     * @param description Event Description.
     * @param date Date of the event, in YYYY-MM-DD format.
     * @param status Status of the event, 'X' is done, otherwise
     *               Undone.
     */
    public Event(String description, LocalDate date, char status){
        super(description);
        this.date = date;
        if (status == 'X') {
            super.mark();
        }
    }

    /**
     * Gets the date of the event.
     *
     * @return Returns the date of the event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + "(" + this.date + ")";
    }

    /**
     * The string representation of event.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

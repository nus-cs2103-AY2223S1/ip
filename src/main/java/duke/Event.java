package duke;

import java.time.LocalDate;

/**
 * Event is a type of task with a date time.
 */
public class Event extends Task {

    private LocalDate timeobject;

    /**
     * Constructor of event.
     *
     * @param description description of event.
     * @param time date of the event.
     */
    public Event(String description, String time) {
        super(description);
        this.timeobject = Parser.stringToDate(time);
    }

    /**
     * Returns string representation of the object to store.
     *
     * @return string representation of the object.
     */
    public String storeToString() {
        return "E|" + this.binarytoString()
                + "|" + this.description.substring(0, description.length()) + "|" + Parser.dateToString(this.timeobject);
    }

    /**
     * Returns string representation of the object.
     *
     * @return string string representation of the object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.displayDate(timeobject) + ")";
    }
}

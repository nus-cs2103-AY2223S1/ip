package duke;

import java.time.LocalDate;


/**
 * Represents the Event task which is a type of task
 * It has the tag [E] and the time would be represented by "at 2022-10-23"
 */
public class Event extends Task {
    private final LocalDate date;

    /**
     * Constructor for Event class
     *
     * @param name       task name
     * @param dateString event date
     */
    public Event(String name, String dateString) {
        super(name);
        this.date = Parser.convertStringToDate(dateString);
    }


    /**
     * Returns the string representation for Event.
     *
     * @return String representation of Event
     */
    @Override
    public String toString() {
        String tag = "[E]";
        return tag + "[" + this.getStatusIcon() + "] "
                + this.getTaskName() + "(" + Parser.convertDateToString(date)
                + ") " + this.getTagString();
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Implements the type of Task that contains a
 * date for the task to be completed at.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.2
 */
public class Event extends Task {

    protected String at;
    protected LocalDate date;

    /**
     * Creates an instance of an Event object.
     *
     * @param description The description for the task that needs to be completed.
     * @param at The date for the task to be completed at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(at);
    }

    /**
     * Check if the given dateTime is equal to that of the Event object.
     *
     * @param at The dateTime to check against.
     * @return boolean Whether the dateTimes are equal.
     */
    public boolean sameTime(String at) {
        LocalDate date = LocalDate.parse(at);
        return this.date.equals(date);
    }

    /**
     * Adds a task to the given taskList input.
     *
     * @param taskList The taskList before a Event Task is added.
     * @return String The String displayed when after the Event Task is added.
     */
    @Override
    public String printAndStoreTask(ArrayList<Task> taskList) {
        taskList.add(this);
        return "Got it. I've added this task:\n"
                + "  " + this + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Gives the String representation of the Event Object.
     *
     * @return String The String format of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
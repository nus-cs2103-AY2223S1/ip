package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Implements the type of Task that contains a
 * deadline for the task to be completed by.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.2
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Creates an instance of a Deadline object.
     *
     * @param description The description for the task that needs to be completed.
     * @param by The deadline for the task to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    /**
     * Check if the given dateTime is equal to that of the Deadline object.
     *
     * @param at The dateTime to check against.
     * @return boolean Whether the dateTimes are equal.
     */
    public boolean sameTime(String at) {
        return false;
    }

    /**
     * Adds a task to the given taskList input.
     *
     * @param taskList The taskList before a Deadline Task is added.
     * @return String The String displayed when after the Deadline Task is added.
     */
    @Override
    public String printAndStoreTask(ArrayList<Task> taskList) {
        taskList.add(this);
        return "Got it. I've added this task:\n"
                + "  " + this + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Gives the String representation of the Deadline Object.
     *
     * @return String The String format of the Deadline Object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}


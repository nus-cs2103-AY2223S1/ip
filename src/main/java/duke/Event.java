package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Event implements the type of Task that contains a
 * date for the task to be completed at.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class Event extends Task {

    protected String at;
    protected LocalDate date;

    /**
     * Event constructor that creates an instance of a Deadline object.
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
     * A method which adds a task to the given taskList input.
     *
     * @param taskList The taskList before a Event Task is added.
     * @return ArrayList of type Task The taskList after the Event Task is added.
     */
    @Override
    public ArrayList<Task> printAndStoreTask(ArrayList<Task> taskList) {
        taskList.add(this);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        return taskList;
    }

    /**
     * The toString method for the Event class.
     *
     * @return String The String format of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
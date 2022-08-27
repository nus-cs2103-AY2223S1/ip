package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Deadline implements the type of Task that contains a
 * deadline for the task to be completed by.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Deadline constructor that creates an instance of a Deadline object.
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
     * A method which adds a task to the given taskList input.
     *
     * @param taskList The taskList before a Deadline Task is added.
     * @return ArrayList of type Task The taskList after the Deadline Taskis added.
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
     * The toString method for the Deadline class.
     *
     * @return String The String format of the Deadline Object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}


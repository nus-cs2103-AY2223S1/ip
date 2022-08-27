package ploopy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores name and date of task. Marks as complete or incomplete
 *
 */

public abstract class Task {
    /** Name of task */
    private final String name;
    /** Boolean for task being done or not */
    private boolean done;
    /** Type of task */
    protected String type;
    /** Date of task (If applicable) */
    private LocalDateTime dateTime;
    /** String format of date */
    private String dateStringForm;
    /** Input string to Date formatter */
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm");
    /** Date to output String formatter */
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy kkmm");

    /**
     * Constructor that takes a name and date
     *
     * @param name Name of the task.
     * @param date Date of the task.
     */
    public Task(String name, String date) {
        this.name = name;
        this.done = false;

        if (date != null) {
            dateStringForm = date;
            this.dateTime = LocalDateTime.parse(date, inputFormatter);
        } else {
            dateStringForm = "";
        }

    }

    /**
     * Returns the string representation of the task.
     *
     * @return the String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", type, getStatus(), name);
    }

    /**
     * Returns a task created using name, date and type.
     *
     * @param type Type of the task.
     * @param name Name of the task.
     * @param date Date of the task.
     * @return Created task.
     */
    public static Task addTaskFromFile(String type, String name, String date) {
        switch (type) {
            case "T":
                return new ToDo(name);
            case "D":
                return new Deadline(name, date);
            default:
                return new Event(name, date);
        }
    }

    /**
     * Marks task as done.
     *
     */
    public void markDone() {
        done = true;
    }

    /**
     * Marks task as incomplete.
     *
     */
    public void unmark() {
        done = false;
    }

    /**
     * String representation of task being complete
     * or not.
     *
     * @return String of task status.
     */
    public String getStatus() {
        return done ? "[✓]" : "[ ]";
    }

    public String getType() {
        return type;
    }

    /**
     * Returns true if task is done and false if not done.
     *
     * @return Task done status.
     */
    public boolean isDone() {
        return done;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns date in a different format as input.
     *
     * @return Formatted date.
     */
    public String getDate() {
        return outputFormatter.format(dateTime);
    }

    /**
     * Returns the date formatted in a specific way for file storage.
     *
     * @return Formatted date.
     */
    public String getDateForFileWrite() {
        return dateStringForm;
    }

}

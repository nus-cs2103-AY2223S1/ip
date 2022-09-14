package ploopy.task;

/**
 * Stores name and date of task. Marks as complete or incomplete
 *
 */

public abstract class Task {
    /** Type of task */
    protected String type;
    /** Name of task */
    protected final String name;
    /** Boolean for task being done or not */
    private boolean isDone;

    /** Priority level of task*/
    private String priority;

    /**
     * Constructor that takes a name and date
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        priority = null;
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
    public static Task of(String type, String name, String date) {
        if (type.equals("todo") || type.equals("T")) {
            return new ToDo(name);
        } else if (type.equals("deadline") || type.equals("D")) {
            return new Deadline(name, date);
        } else {
            return new Event(name, date);
        }
    }

    /**
     * Marks task as done.
     *
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks task as incomplete.
     *
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * String representation of task being complete
     * or not.
     *
     * @return String of task status.
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
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
        return isDone;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns date in a different format as input.
     *
     * @return Formatted date.
     */
    public abstract String getDate();

    /**
     * Returns the date formatted in a specific way for file storage.
     *
     * @return Formatted date.
     */
    public abstract String getDateForFileWrite();

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriorityForString() {
        if (priority == null || priority.equals("none")) {
            return "";
        } else {
            return " Priority: " + priority;
        }
    }

    public int getPriorityForFile() {
        if (priority == null || priority.equals("none")) {
            return 0;
        } else {
            return 1;
        }
    }
}

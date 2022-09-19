package pony.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected static String SEPARATOR = " | ";
    protected static DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected static DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as uncompleted.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public abstract String saveToDisk();

    //...
}
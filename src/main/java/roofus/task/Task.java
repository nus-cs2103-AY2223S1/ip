package roofus.task;

import java.time.LocalDate;

/**
 * Represents a Task object containing a description and a
 * boolean value indicating if the task has been completed.
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs an instance of Task.
     *
     * @param description A description of the task instance.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if Task instance has a LocalDate attribute.
     *
     * @return boolean Returns true if Task is a Deadline or Event and false otherwise.
     */
    public boolean hasTime() {
        return false;
    }

    /**
     * Retrieves the Task's LocalDate attribute.
     *
     * @return LocalDate Returns the LocalDate attribute of the Task.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Marks the Task as completed.
     */
    public void mark() {
        isDone = true;;
    }

    /**
     * Marks the Task as incomplete.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Formats the Task into a string that can
     * be written to Roofus's memory.
     *
     * @return String A string representing the Task to be saved.
     */
    public String writeString() {
        return new String();
    }

    /**
     * Formats the Task into a string that is printed
     * out to users.
     *
     * @return String A string representing the Task to be printed.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * Compares tasks using their LocalDate attribute.
     *
     * @param t The task to be compared with the current instance.
     * @return int Number representing the priority.
     */
    @Override
    public int compareTo(Task t) {
        if (this.hasTime() && t.hasTime()) {
            return this.getDate().compareTo(t.getDate());
        } else if (this.hasTime()) {
            return -1;
        } else if (t.hasTime()) {
            return 1;
        } else {
            return 0;
        }
    }
}

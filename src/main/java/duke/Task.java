package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Takes in a description for the task
     * @param description The task description
     * */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String formatToSave() {
        return "";
    }

    /**
     * Checks if task description contains a string
     *
     * @param str String to check against.
     * @return true if it contains and false otherwise.
     */
    public boolean contains(String str) {
        return this.description.contains(str);
    }

    /**
     * Returns a String representation of the task
     * @return string
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
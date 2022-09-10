package mort.task;

import java.time.LocalDate;

/**
 * Class that represents a task.
 */
public abstract class Task {
    /** Description of the task */
    protected String desc;
    /** Completion status of the task */
    protected boolean isDone;

    /**
     * Constructor to initialize the description and completion status of the task.
     * Completion status is always false when task is first created.
     * @param desc The task description
     */
    
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Constructor to initialize the description and completion status of the task.
     * @param desc The task description.
     * @param isDone The task completion status.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Gets the string representation of the task status.
     * @return The string representation of the task status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as complete.
     */
    public String mark() {
        StringBuilder sb = new StringBuilder();
        if (this.isDone) {
            sb.append("You can't finish the same task twice, genius.\n");
        } else {
            sb.append("You really took your time with this one, didn't you?\n");
            this.isDone = true;
        }
        return sb.append("  ").append(this).append("\n").toString();
    }

    /**
     * Marks the task as incomplete.
     */
    public String unmark() {
        StringBuilder sb = new StringBuilder();
        if (!this.isDone) {
            sb.append("You're trying to unmark a task you haven't done.\n"
                    + "Let that sink in for a moment.\n");
        } else {
            sb.append("And here I was thinking you were getting somewhere...\n");
            this.isDone = false;
        }
        return sb.append("  ").append(this).append("\n").toString();
    }

    /**
     * Checks if the given keyword is contained within the task.
     * @param keyword The given keyword to be matched.
     * @return True if there is a match; false otherwise.
     */
    public boolean isMatch(String keyword) {
        return this.toString().toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns the task as a string in its saved format.
     * @return The string representation of the saved format of the task.
     */
    public String getSaveFormat() {
        return "| " + (isDone ? 1 : 0) + " | " + this.desc;
    }
    

    /**
     * Checks if a given date matches date of the task.
     * @param date
     * @return True if there is a match; false otherwise.
     */
    public abstract boolean isDateMatch(LocalDate date);
    
    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.desc;
    }
}

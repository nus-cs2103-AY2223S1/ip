package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toFileDescription() {
        return (this.isDone ? "1 " : "0 ") + "| " + this.description;
    }

    public static Task fromFileDescription(String input) {
        return null;
    }
    public boolean isHappeningOnDate(LocalDate localDate) {
        return false;
    }

    /**
     * Returns a boolean to check if keyword specified by user is in the task's description.
     *
     * @param keyword the keyword entered by the user
     * @return true if and only if the keyword is contained in the description
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

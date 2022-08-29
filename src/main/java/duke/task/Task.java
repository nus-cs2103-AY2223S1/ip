package duke.task;

/**
 * Represents a Task with a description and a completion status.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a Task with the given description.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets completion status of this task to true.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Prints the completion status and the description of the task.
     *
     * @return The completion status and description of the task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        String line = String.format("[%s] %s", status, this.description);
        return line;
    }

    /**
     * Returns the command to be saved in the save file.
     *
     * @return The String representing the command of this task.
     */
    public String toCommand() {
        String completionStatus = this.isDone ? "1" : "0";
        return completionStatus + " | " + this.description;
    }

    /**
     * Marks the task as completed and prints the task's description.
     * If the task was already marked as completed, tell the user.
     *
     * @return The String message for successfully marking a task.
     */
    public String mark() {
        if (this.isDone) {
            return "Duke: This task has already been marked as done.";
        }
        completeTask();
        return "Duke: Nice! I've marked this task as done:\n" + this;
    }

    /**
     * Marks the task as completed and prints the task's description.
     * If the task was already marked as completed, tell the user.
     *
     * @return The String message for successfully marking a task.
     */
    public String unmark() {
        if (!this.isDone) {
            return "Duke: This task has already been marked as not done.";
        }
        this.isDone = false;
        return "Duke: OK, I've marked this task as not done yet:\n" + this;
    }

    /**
     * Checks if the Task's description contains the given keyword.
     *
     * @param keyword The keyword to search for in the task.
     * @return true if the keyword is in the task's description, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}

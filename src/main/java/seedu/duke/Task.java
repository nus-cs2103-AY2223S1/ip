package seedu.duke;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor that creates an instance of Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Sets this task as done or not done, depending on the given command.
     *
     * @param command The command that determines whether this task is done or not done.
     * @return A response to be displayed to the user.
     */
    public String markOrUnmarkAsDone(String command) {
        assert command != null : "No command provided.";

        if (command.startsWith("mark ")) {
            this.isDone = true;
            return "Nice! I've marked this task as done: \n" + this;
        } else if (command.startsWith("unmark ")) {
            this.isDone = false;
            return "OK, I've marked this task as not done yet: \n" + this;
        }

        return "Oops! Something went wrong!";
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a String that reflects whether this task is done or not done.
     *
     * @return Returns an "X" or " " depending on whether this task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "  "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon()  + "] " + description;
    }

    /**
     * Returns a String that is written to a file to be stored.
     *
     * @return A String formatted to be stored.
     */
    public String toStore() {
        if (isDone) {
            return " : 1 : " + description;
        } else {
            return " : 0 : " + description;
        }
    }

    /**
     * Checks whether this Task's description contains the String to be found.
     * @param toMatch The String to be found.
     * @return true if the description contains the String to be found, false otherwise.
     */
    public boolean checkMatching(String toMatch) {
        assert toMatch != null : "String to match not provided.";

        return description.contains(toMatch);
    }
}

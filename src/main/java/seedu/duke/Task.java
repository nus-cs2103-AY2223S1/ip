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
     */
    public void toggleDone(String command) {
        if (command.startsWith("mark ")) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done: \n" + this.toString());
        } else if (command.startsWith("unmark ")) {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet: \n" + this.toString());
        }
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
        return (isDone ? "X" : " "); // mark done task with X
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
}

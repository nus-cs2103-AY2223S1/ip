package duke;

/**
 * Task class that Deadline, Todo and Event extends from
 * @author amresh A0235398R
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that initializes Task object
     * @param description Descripion of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return icon to state whether task is completed
     * @return Return string to signify marked or not marked
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Alters isDone field to signify marked
     * @return Return string to show its done
     */
    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n\t[X] " + this.description;
    }

    /**
     * Alters isDone field to signify unmarked
     * @return Return string to show its not done
     */
    public String markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t[ ] " + this.description);
        return "OK, I've marked this task as not done yet:\n\t[ ] " + this.description;
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

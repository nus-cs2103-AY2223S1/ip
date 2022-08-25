package aRC;

/**
 * Encapsulates a Task object
 */
public abstract class Task {

    protected String title;
    protected boolean isDone;

    /**
     * Constructor for aRC.Task
     * @param title The title of the aRC.Task
     * @param isDone The isDone status of the aRC.Task
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Marks this task as done
     */
    public void mark() {
        this.isDone = true;
        System.out.println(String.format("Nice! I've marked this task as done:\n\t%s", this));
    }

    /**
     * Marks this task as not done
     */
    public void unmark() {
        this.isDone = false;
        System.out.println(String.format("Ok, I've marked this task as not done yet:\n\t%s", this));
    }

    /**
     * Returns how a Task should be represented
     * @return String representation of Task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.title);
    }

    /**
     * Returns how a Task should be stored in a txt file
     * @return String representation of Task
     */
    public abstract String fileFormat();
}
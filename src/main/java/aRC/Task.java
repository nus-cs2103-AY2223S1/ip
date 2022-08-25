package arc;

public abstract class Task {
    /**
     * The title of the aRC.Task
     */
    protected String title;

    /**
     * The completion status of the aRC.Task
     */
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
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
        System.out.println(String.format("Nice! I've marked this task as done:\n\t%s", this));
    }

    /**
     * Marks the task as not done
     */
    public void unmark() {
        this.isDone = false;
        System.out.println(String.format("Ok, I've marked this task as not done yet:\n\t%s", this));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.title);
    }

    public abstract String fileFormat();
}
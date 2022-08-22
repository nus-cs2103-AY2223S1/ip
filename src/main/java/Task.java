public abstract class Task {
    /**
     * The title of the Task
     */
    private String title;

    /**
     * The completion status of the Task
     */
    private boolean isDone;

    /**
     * Constructor for Task
     * @param title The title of the Task
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
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
}
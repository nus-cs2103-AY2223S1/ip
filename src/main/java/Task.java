public class Task {
    protected String detail;
    protected boolean isDone;

    /**
     * Constructor for task.
     * @param detail String
     */
    public Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    /**
     * Get icon for status done or not
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Get detail of the task
     * @return String
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Mark task as Done and Print acknowledge message.
     */
    protected void markAsDone() {
        System.out.println("Good job! Marked this task as Done");
        this.isDone = true;
        System.out.println(this.getStatusIcon() + " " + this.detail);
    }

    /**
     * Mark task as not Done and Print acknowledge message.
     */
    protected void unmark() {
        System.out.println("Marked this task as not Done yet!");
        this.isDone = false;
        System.out.println(this.getStatusIcon() + " " + this.detail);
    }
}

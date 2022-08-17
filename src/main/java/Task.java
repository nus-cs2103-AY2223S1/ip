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
        System.out.println(Constants.MARK_AS_DONE_MESSAGE);
        this.isDone = true;
        System.out.println(this.toString());
    }

    /**
     * Mark task as not Done and Print acknowledge message.
     */
    protected void unmark() {
        System.out.println(Constants.UNMARK_MESSAGE);
        this.isDone = false;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.detail;
    }
}

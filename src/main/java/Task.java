public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("     Task has been marked as done!: \n"
                          + "       "
                          + this.toString());
    }

    public void markAsUnDone() {
        this.isDone = false;
        System.out.println("     Task has been marked as NOT done!: \n"
                + "       "
                + this.toString());
    }

    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }
}

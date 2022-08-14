public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        if (this.isDone) {
            System.out.println("This task has already been done :|");
        } else {
            this.isDone = true;
            System.out.println("Mission complete! Nice ah\n" + this);
        }
    }

    public void unMark() {
        if (!this.isDone) {
            System.out.println("An unfinished task cannot be unmark...");
        } else {
            this.isDone = false;
            System.out.println("Looks like there is more work to do\n" + this);
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
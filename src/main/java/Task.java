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

    public void setDone(boolean x) {
        this.isDone = x;
    }

    public void mark() {
        this.setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + this);
    }

    public void unmark() {
        this.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + this);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

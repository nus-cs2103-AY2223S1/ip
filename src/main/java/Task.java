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
        System.out.println("Nice! I've marked this task as done:\n" +
                String.format("   [X] %s", this.description));
    }

    public void unmark() {
        this.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                String.format("   [ ] %s", this.description));
    }

    @Override
    public String toString() {
        return this.description;
    }
}

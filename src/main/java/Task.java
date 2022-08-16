public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✔️" : " "); // mark done task with X
    }

    public void setStatusIcon(boolean status) {
        this.isDone = status;
    }

    public void unmark() {
        this.setStatusIcon(false);
        System.out.println("Marked as uncompleted!\n  " + this.toString());
    }

    public void mark() {
        this.setStatusIcon(true);
        System.out.println("Yayy! Marked as completed :D\n  " + this.toString());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

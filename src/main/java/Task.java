public class Task {
    protected String description;
    protected boolean done = false;

    protected Task(String task) {
        this.description = task;
    }
    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }
    public String getDescription() {
        return this.description;
    }
    public String getDone() {
        return (done==true? "[X] " : "[ ] ");
    }
    public String toString() {
        return (this.getDone() + this.getDescription());
    }
}

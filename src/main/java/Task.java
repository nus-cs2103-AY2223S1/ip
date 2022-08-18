public class Task {
    private String description;
    private boolean done = false;

    Task(String task) {
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
    public String print() {
        return (this.getDone() + this.getDescription());
    }
}

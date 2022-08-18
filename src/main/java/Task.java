public class Task {
    protected String description;
    protected boolean isDone;
    static int numTask;

    public Task(String description) {
            this.description = description;
            this.isDone = false;
            numTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true; //mark
    }

    public void markAsNotDone() {
        this.isDone = false; //unmark
    }

    public void removeTask() {
        numTask--;
    }

    public String toString() {
        return "["+ this.getStatusIcon() +"] " + this.description;
    }

}
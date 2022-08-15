public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description =  description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone(){
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
}

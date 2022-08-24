public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setStatus(int status) {
        if (status == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }
}
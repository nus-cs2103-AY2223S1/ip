package Duke;

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

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String changeFormat() {
        String done = isDone ? "1" : "0";
        return "|" + done + "|" + this.description;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}

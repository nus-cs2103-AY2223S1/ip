package chacha.tasks;
public class Task {
    private String description;
    private boolean isDone;
    private String type;
    private String date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
        this.date = "";
    }

    public Task() {
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
     
}

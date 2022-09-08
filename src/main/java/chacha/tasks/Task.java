package chacha.tasks;

import java.time.LocalDateTime;

public class Task {
    private String description;
    private boolean isDone;
    private String type;
    private LocalDateTime date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
        this.date = null;
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
        return (isDone ? "X" : " "); 
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }
     
}

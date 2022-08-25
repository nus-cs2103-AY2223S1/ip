public class Todo extends Task {
    private String description;
    private boolean isDone;
    private String type;

    public Todo(String description) {
        this.description = description;
        this.type = "T";
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

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description;
    }
     
}

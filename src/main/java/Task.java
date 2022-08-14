public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String timing;

    public Task(String description, String type, String timing) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.timing = timing;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getName() {
        if (this.isDone && this.type.equals("T")) {
            return "[T][X] " + this.description;
        }
        else if (!this.isDone && this.type.equals("T")) {
            return "[T][] " + this.description;
        }
        else if (this.isDone && this.type.equals("D")) {
            return "[D][X] " + this.description + "(by: " + this.timing + ")";
        }
        else if (!this.isDone && this.type.equals("D")) {
            return "[D][] " + this.description + "(by: " + this.timing + ")";
        }
        else if (this.isDone && this.type.equals("E")) {
            return "[E][X] " + this.description + "(at: " + this.timing + ")";
        }
        else if (!this.isDone && this.type.equals("E")) {
            return "[E][] " + this.description +   "(at: " + this.timing + ")";
        }
        return null;
    }

    //...
}
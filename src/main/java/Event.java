public class Event extends Task {
    private String description;
    private String range;
    private boolean isDone;
    private String type;

    public Event(String description, String range) {
        this.description = description;
        this.range = range;
        this.type = "E";
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

    public String getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description + "(at: " + range + ")";
    }
     
}

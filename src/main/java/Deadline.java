public class Deadline extends Task {
    private String description;
    private String date;
    private boolean isDone;
    private String type;

    public Deadline(String description, String date) {
        this.description = description;
        this.date = date;
        this.type = "D";
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

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description + "(by: " + date + ")";
    }
     
}

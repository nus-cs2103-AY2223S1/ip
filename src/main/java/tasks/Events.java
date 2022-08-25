package tasks;

public class Events extends Task {
    protected String description;
    protected boolean isDone;
    protected String timing;

    public Events(String description, String timing) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.timing = timing;
    }


    public String toString() {
        String result = "[E]" + "[" + getStatusIcon() + "] " + this.description + " (at: " + this.timing + ")";
        return result;
    }

    public String fileString() {
        String write = "E / " + (isDone ? "1 / " : "0 / ") + this.description.strip() + " / " + this.timing.strip();
        return write;
    }
}

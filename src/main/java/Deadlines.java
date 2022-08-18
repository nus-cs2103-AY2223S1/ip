public class Deadlines extends Task {
    protected String description;
    protected boolean isDone;
    protected String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
    }


    public String toString() {
        String result = "[D]" + "[" + getStatusIcon() + "] " + this.description + "(by:" + this.deadline + ")";
        return result;
    }
}

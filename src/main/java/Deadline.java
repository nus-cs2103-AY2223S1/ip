public class Deadline extends Task{
    protected String description;
    protected boolean isDone;
    protected String date;

    public Deadline(String description, String date) {
        this.description = description;
        this.date = date;
    }

    public void isMark(boolean bool) {
        this.isDone = bool;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " +
                this.description + " (by: " + this.date + ")";
    }
}

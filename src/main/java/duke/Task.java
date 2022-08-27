package duke;

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

    /**
     * Returns the new format of String
     * to be loaded
     *
     * @return String
     */
    public String changeFormat() {
        String done = isDone ? "1" : "0";
        return "|" + done + "|" + this.description;
    }

    /**
     * Returns the String of the Object
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}

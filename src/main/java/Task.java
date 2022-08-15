/**
 * A class that represents a task in the task list.
 */
public class Task {
    protected String text = "";
    protected boolean complete = false;

    public Task(String text) {
        this.text = text;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "[" + (complete ? "X" : " ") + "] " + text;
    }
}
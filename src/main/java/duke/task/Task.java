package duke.task;
public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void complete() {
        this.isDone = true;
    }

    public void undo() {
        this.isDone = false;
    }


    public String tofileString() {
        return this.isDone
                ? "1|" + this.desc
                : "0|" + this.desc;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.desc;
    }
}
